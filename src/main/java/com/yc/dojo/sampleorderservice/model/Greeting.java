package com.yc.dojo.sampleorderservice.model;

public class Greeting {
    private final String greeting;
    
    public Greeting(String greeting) {
        this.greeting = greeting;
    }
    
    public String getGreeting() {
        return greeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Greeting greeting1 = (Greeting) o;

        return greeting != null ? greeting.equals(greeting1.greeting) : greeting1.greeting == null;
    }

    @Override
    public int hashCode() {
        return greeting != null ? greeting.hashCode() : 0;
    }
}
