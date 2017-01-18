package net.lim.strategies;

import java.io.Closeable;

/**
 * Created by Limmy on 09.01.2017.
 */
public interface Strategy extends Closeable {
    void create(String name);
    int read(String name);
    void update(String name);
    //Delete is not necessary.
    void close();
}
