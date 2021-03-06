package org.grapheus.web.model.loader;

import org.grapheus.web.RemoteUtil;
import org.grapheus.web.model.GraphInfo;

import java.util.List;
import java.util.stream.Collectors;

public class AvailableGraphsLoader {
    public List<GraphInfo> loadAvailableGraphs() {
        return RemoteUtil.graphsAPI().getAvailableGraphs().stream()
                .map(rg -> GraphInfo.builder()
                        .graphId(rg.getGraphId())
                        .editPermitted(rg.isEditPermitted())
                        .sourceGraphId(rg.getGenerativeGraphId())
                        .sourceGraphProperty(rg.getGenerativeProperty())
                        .build())
                .collect(Collectors.toList());
    }
}
