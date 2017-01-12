package net.lim.strategies;

/**
 * Created by Limmy on 09.01.2017.
 */
public interface Strategy {
    void create(String name);
    int read(String name);
    void update(String name);
    //Delete is not necessary.
}
