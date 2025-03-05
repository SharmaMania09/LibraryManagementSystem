package com.project.LibraryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.SystemHealth;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class ActuatorController {

    @Autowired
    private HealthEndpoint healthEndpoint;

    @Autowired
    private InfoEndpoint infoEndpoint;

    @Autowired
    private MetricsEndpoint metricsEndpoint;

    @GetMapping("/actuators")
    public String actuatorDashboard(Model model) 
    {
        // ✅ 1. Fetch System Health (Fix type mismatch in Spring Boot 3)
        HealthComponent healthComponent = healthEndpoint.health();
        String healthStatus = healthComponent.getStatus().toString();

        // Extract details from SystemHealth
        Map<String, Object> healthDetails = new LinkedHashMap<>();
        if (healthComponent instanceof SystemHealth systemHealth) {
            systemHealth.getComponents().forEach((key, value) -> 
                healthDetails.put(key, value.getStatus().toString())
            );
        }

        // ✅ 2. Fetch Application Info
        Map<String, Object> info = infoEndpoint.info();

        // ✅ 3-10. Fetch Actuator Metrics (at least 10 properties)
        Map<String, Object> metricsData = new LinkedHashMap<>();
        metricsData.put("JVM Memory Used (bytes)", getMetricValue("jvm.memory.used"));
        metricsData.put("JVM Memory Max (bytes)", getMetricValue("jvm.memory.max"));
        metricsData.put("JVM GC Count", getMetricValue("jvm.gc.pause.count"));
        metricsData.put("JVM Threads", getMetricValue("jvm.threads.live"));
        metricsData.put("System CPU Usage", getMetricValue("system.cpu.usage"));
        metricsData.put("Process CPU Usage", getMetricValue("process.cpu.usage"));
        metricsData.put("HTTP Requests (Total)", getMetricValue("http.server.requests"));
        metricsData.put("Database Connection Usage", getMetricValue("db.connections.usage"));
        metricsData.put("Cache Hits", getMetricValue("cache.gets"));
        metricsData.put("Cache Misses", getMetricValue("cache.misses"));

        // ✅ Add Attributes for Thymeleaf Rendering
        model.addAttribute("healthStatus", healthStatus);
        model.addAttribute("healthDetails", healthDetails);
        model.addAttribute("info", info);
        model.addAttribute("metrics", metricsData);

        return "actuators";  // Render actuators.html
    }

    // Helper method to get metric values safely
    private Object getMetricValue(String metricName) {
        var metric = metricsEndpoint.metric(metricName, null);
        return (metric != null && !metric.getMeasurements().isEmpty()) ? 
               metric.getMeasurements().get(0).getValue() : "N/A";
    }
}
