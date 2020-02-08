package com.example.hackathonbaldragas.domain;

import java.time.LocalDate;
import java.util.List;

public class Category {
    private String name;
    private String description;
    //private List<String> categoriesList;

    public Category() {}

    public Category(CategoryBuilder builder) {
        name = builder.name;
        description = builder.description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name=" + name +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Category)) return false;
        Category u = (Category) o;
        return u.name.equals(this.name);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }


    public static class CategoryBuilder {
        private String name;
        private String description;

        public CategoryBuilder() {}

        public CategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}