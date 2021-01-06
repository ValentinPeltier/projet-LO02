package fr.utt.lo02.tdvp.model.observer;

import java.util.ArrayList;

public abstract class Observable {
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public boolean deleteObserver(Observer observer) {
        return this.observers.remove(observer);
    }

    public void notifyObservers(Object arg) {
        for (Observer observer : this.observers) {
            observer.update(this, arg);
        }
    }
}
