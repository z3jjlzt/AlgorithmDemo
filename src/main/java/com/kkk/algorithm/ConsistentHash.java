package com.kkk.algorithm;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.CRC32;

/**
 * 一致性hash算法
 * Created by z3jjlzt on 2018/6/6.
 */
public class ConsistentHash {
    static class ServerNode {
        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("ServerNode{");
            sb.append("ip='").append(ip).append('\'');
            sb.append('}');
            sb.append("   " + hashCode());
            return sb.toString();
        }

        public ServerNode() {
        }

        public ServerNode(String ip) {

            this.ip = ip;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        private String ip;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ServerNode that = (ServerNode) o;

            return ip != null ? ip.equals(that.ip) : that.ip == null;
        }

        @Override
        public int hashCode() {
            CRC32 crc32 = new CRC32();
            crc32.update(ip.getBytes());
            int abs = (int) crc32.getValue();
            return ip != null ? Math.abs(abs) : 0;
        }
    }

    //使用LinkList实现
    private List<ServerNode> consistentRing = new LinkedList<>();
    private static final int VIRTUAL_NUM = 10;

    private static Map<String, ServerNode> cache = new ConcurrentHashMap<>();

    //添加server服务节点到一致环上
    public boolean addServer(String node) {
        int size = consistentRing.size();
        if (size <= 0) {
            for (int i = 0; i < VIRTUAL_NUM; i++) {
                ServerNode serverNode = new ServerNode(node + "#V" + i);
                consistentRing.add(i, serverNode);
            }
            consistentRing.sort((a, b) -> a.hashCode() > b.hashCode() ? 1 : -1);
            return true;
        }
        for (int j = 0; j < VIRTUAL_NUM; j++) {
            ServerNode serverNode = new ServerNode(node + "#V" + j);
            int hashCode = Math.abs(serverNode.hashCode());
            boolean add = false;
            for (int i = 0; i < consistentRing.size(); i++) {
                if (consistentRing.get(i).hashCode() < hashCode) {
                    continue;
                }
                synchronized (this) {
                    for (Map.Entry<String, ServerNode> entry : cache.entrySet()) {
                        if (entry.getValue().getIp().equals(consistentRing.get(i).getIp())) {
                            cache.remove(entry.getKey());
                            System.out.println(entry.getKey() + "缓存失效");
                        }

                    }
                    consistentRing.add(i, serverNode);
                    add = true;
                }
                break;
            }
            if (!add)
                consistentRing.add(consistentRing.size(), serverNode);
            if (j == VIRTUAL_NUM - 1)
                return true;
        }
        for (int i = 0; i < VIRTUAL_NUM; i++) {
            consistentRing.add(new ServerNode(node + "#V" + i));
        }
        return true;
    }

    //移除server服务节点
    public boolean removeServer(String node) {
        boolean removeall = true;
        for (int i = 0; i < VIRTUAL_NUM; i++) {
            String server = node + "#V" + i;
            boolean ri = consistentRing.remove(new ServerNode(server));
            for (Map.Entry<String, ServerNode> entry : cache.entrySet()) {
                if (server.equals(entry.getValue().getIp())) {
                    cache.remove(entry.getKey());
                    System.out.println(entry.getKey() + "缓存失效");
                }
            }
            if (ri == false)
                removeall = false;
        }
        return removeall;
    }

    //通过客户端ip找到映射的服务节点
    public ServerNode getServerNode(String ip) {
        if (consistentRing.size() <= 0)
            throw new RuntimeException("没有可用服务");
        int hashCode = Math.abs(ip.hashCode());
        if (cache.containsKey(ip))
            return cache.get(ip);
        ServerNode serverNode = consistentRing.stream()
                .filter(x -> x.hashCode() > hashCode)
                .findFirst()
                .orElse(consistentRing.get(0));
        cache.put(ip, serverNode);
        return serverNode;
    }

    public static void main(String[] args) {
        ConsistentHash consistentHash = new ConsistentHash();
        String[] server_ip = {"127.0.0.100", "127.0.0.101", "127.0.0.102", "127.0.0.103", "127.0.0.104"};
        String[] client_ip = {"2.3.3.4", "210.3.5.100", "255.0.0.101", "160.0.0.102", "12.0.4.103", "1.0.0.107", "1.0.0.13"};
        for (String s : server_ip) {
            consistentHash.addServer(s);
        }
        for (String s : client_ip) {
            System.out.println(s + "  " + Math.abs(s.hashCode()) + "      " + consistentHash.getServerNode(s));
        }
        System.out.println("---------------");
        consistentHash.removeServer(server_ip[1]);
        for (String s : client_ip) {
            System.out.println(s + "  " + Math.abs(s.hashCode()) + "      " + consistentHash.getServerNode(s));
        }
        System.out.println("---------------");
        consistentHash.addServer("127.0.0.105");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < consistentHash.consistentRing.size(); i++) {
            sb.append(consistentHash.consistentRing.get(i).hashCode() + " ");
        }
        System.out.println(sb.toString());
        for (String s : client_ip) {
            System.out.println(s + "  " + Math.abs(s.hashCode()) + "      " + consistentHash.getServerNode(s));
        }
    }

    private static void swap(Integer a1, Integer a2) {
        try {
            int t = a1.intValue();
            Field aaValue = a1.getClass().getDeclaredField("value");
            aaValue.setAccessible(true);
            aaValue.set(a1, a2.intValue());
            Field bbValue = a2.getClass().getDeclaredField("value");
            bbValue.setAccessible(true);
            //这里的1不会自动装箱，当然就不会从缓存中去取值
            bbValue.set(a2, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
