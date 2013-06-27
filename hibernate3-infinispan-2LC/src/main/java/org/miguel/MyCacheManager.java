package org.miguel;

import org.jboss.cache.*;

/**
 * Hello world!
 */
public class MyCacheManager {

    private Cache cache;
    private Node fredrikNilsson;
    private Node rootNode;
    private Fqn fredrikNilssonFqn;

    public Node getFredrikNilsson() {
        return fredrikNilsson;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public Fqn getFredrikNilssonFqn() {
        return fredrikNilssonFqn;
    }

    public void createCache() {
        System.out.print("Starting...\n");
        CacheFactory factory = new DefaultCacheFactory();
        cache = factory.createCache("cache-configuration.xml");

        /*Configuration config = cache.getConfiguration();
        config.setClusterName("cluster1");*/

        cache.create();
        cache.start();
        System.out.print("Cache created and started\n");
    }

    /**
     * A Node should be viewed as a named logical grouping of data. A node should be used to contain data
     * for a single data record, for example information about a particular person or account. It should be kept
     * in mind that all aspects of the cache - locking, cache loading, replication and eviction - happen on a per-
     * node basis. As such, anything grouped together by being stored in a single node will be treated as a single atomic unit
     */
    public void addNode() {
        // Let us get a hold of the root node.
        rootNode = cache.getRoot();

        // Remember, JBoss Cache stores data in a tree structure.
        // All nodes in the tree structure are identified by Fqn objects.
        fredrikNilssonFqn = Fqn.fromString("/nilsson/fredrik");

        // Create a new Node
        fredrikNilsson = rootNode.addChild(fredrikNilssonFqn);

        // let us store some data in the node
        fredrikNilsson.put("isJavaArchitect", Boolean.TRUE);
        fredrikNilsson.put("favoriteDrink", new Coffee());
        /* some tests (just assume this code is in a JUnit test case) */

        System.out.print("Added entry..\n");

    }

    public void removeKeyFromNode(Node someNode, String someKey) {
        // let us remove some data from the node
        someNode.remove(someKey);
        System.out.print("Removed favoriteDrink..\n");

    }

    /**
     * A Fully Qualified Name (Fqn) encapsulates a list of names which represent a path to a particular location
     * in the cache's tree structure. T he elements in the list are typically Strings but can be any Object or a
     * mix of different types.
     * @param someFqn Fully Qualified Name
     */
    public void removeChild(Fqn someFqn) {
        // let us remove the node altogether
        rootNode.removeChild(someFqn);
        System.out.print("Removed child..\n");
    }


    /**
     * It is good practice to stop and destroy your cache when you are done using it, particularly if it is a
     * clustered cache and has thus used a JGroups channel. Stopping and destroying a cache ensures
     * resources like network sockets and maintenance threads are properly cleaned up.
     */
    public void destroyCache(){
        cache.stop();
        cache.destroy();
        System.out.print("Destroyed cache..\n");
    }


}
