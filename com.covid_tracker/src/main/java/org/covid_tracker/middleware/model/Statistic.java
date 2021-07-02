package org.covid_tracker.middleware.model;

import java.util.Objects;

public class Statistic {

    private int confirmed;
    private int deaths;
    private int active;
    private int recovered;

    public Statistic(int confirmed, int deaths, int active, int recovered) {
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.active = active;
        this.recovered = recovered;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getRecovered() {
        return active;
    }

    public void setRecovered(int active) {
        this.recovered = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return confirmed == statistic.confirmed && deaths == statistic.deaths && active == statistic.active && recovered == statistic.recovered;
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmed, deaths, active, recovered);
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", active=" + active +
                ", recovered=" + recovered +
                '}';
    }
}
