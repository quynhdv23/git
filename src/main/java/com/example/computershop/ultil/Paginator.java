package com.example.computershop.ultil;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@With
@NoArgsConstructor
@AllArgsConstructor
public class Paginator {
    public Optional<Integer> pageOpt = Optional.empty();
    public Optional<Integer> sizeOpt = Optional.empty();
    public List<Ordering> orderings = new ArrayList<>();

    public void setPage(Integer page) {
        this.pageOpt = Optional.ofNullable(page);
    }

    public void setSize(Integer size) {
        this.sizeOpt = Optional.ofNullable(size);
    }

    public void setOrdering(String name, Boolean desc) {
        this.orderings.add(new Ordering(name, desc));
    }

    public void addOrdering(String name, Boolean desc) {
        this.orderings.add(new Ordering(name, desc));
    }

    public void deleteOrdering(String name) {
        this.orderings = this.orderings.stream().filter(o -> !o.getName().equals(name)).collect(Collectors.toList());
    }

    public static class Ordering {
        private String name;
        private Boolean desc;

        public Ordering(String name, Boolean desc) {
            this.name = name;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getDesc() {
            return desc;
        }

        public void setDesc(Boolean desc) {
            this.desc = desc;
        }
    }
}
