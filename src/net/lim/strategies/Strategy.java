package net.lim.strategies;

/**
 * Created by Limmy on 09.01.2017.
 */
public interface Strategy {
    void create(String name);
    int read(String name);
    void write(String name);
    //Update isn't necessary because of the same realization of write.
}
