package com.example.hackathonbaldragas.domain;

public class Request {
    private String user_dni;
    private String id;
    private String state;
    private String description;

    public Request() {}

    public Request(RequestBuilder builder) {
        user_dni = builder.user_dni;
        id = builder.id;
        state = builder.state;
        description = builder.description;
    }

    @Override
    public String toString() {
        return "Request{" +
                "user_dni=" + user_dni +
                ", id=" + id +
                ", state=" + state +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Request)) return false;
        Request u = (Request) o;
        return u.user_dni.equals(this.user_dni) && u.id.equals(this.id);
    }

    public String getUser_dni() {
        return user_dni;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public void setUser_dni(String user_dni) { this.user_dni = user_dni; }

    public void setId(String id) { this.id = id; }

    public void setState(String state) { this.state = state; }

    public void setDescription(String description) { this.description = description; }


    public static class RequestBuilder {
        private String user_dni;
        private String id;
        private String state;
        private String description;

        public RequestBuilder() {}

        public RequestBuilder user_dni(String user_dni) {
            this.user_dni = user_dni;
            return this;
        }

        public RequestBuilder id(String id) {
            this.id = id;
            return this;
        }

        public RequestBuilder state(String state) {
            this.state = state;
            return this;
        }

        public RequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }
}