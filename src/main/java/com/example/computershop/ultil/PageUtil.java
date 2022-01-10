package com.example.computershop.ultil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PageUtil {
    public static String toPaginatorSQL(Paginator paginator) {
        String query = " ";
        // process orderBy
        if (paginator.orderings.size() > 0) {
            query += " ORDER BY " + paginator.orderings.stream().map(o ->
                    parseName(o.getName()) + (o.getDesc() ? " DESC" : " ASC")
            ).collect(Collectors.joining(", "));
        }
        if (paginator.sizeOpt.isPresent()) {
            if (paginator.sizeOpt.get() > 0) {
                query += " LIMIT " + paginator.sizeOpt.get();
            }
            query += paginator.pageOpt.map(page -> " OFFSET " + (page - 1) * paginator.sizeOpt.get()).orElse("");
        }
        return query;
    }
    public static String parseName(String name) {
//        return Arrays.stream(name.split("\\.")).map(s -> String.format("%s", s)).collect(Collectors.joining("."));
        String[] strings = name.split("\\.");
        if(strings.length>1)
        {
            List<String> asList = Arrays.asList(strings);
            List<String> collect = asList.stream().map(x-> x.trim()).collect(Collectors.toList());
            return String.join(".", collect);
        }
        else
        {
            return name.trim();
        }
    }
    public static String buildPaginationSql(int page, int size, String orderBy, Boolean desc) {
        final Paginator paginator = new Paginator();
        paginator.setSize(size);
        paginator.setPage(page);
        paginator.setOrdering(orderBy, desc);
        return toPaginatorSQL(paginator);
    }
}