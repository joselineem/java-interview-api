package com.talentreef.interviewquestions.takehome.controllers;

import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.services.WidgetService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/v1/widgets", produces = MediaType.APPLICATION_JSON_VALUE)
public class WidgetController {

  private final WidgetService widgetService;

  public WidgetController(WidgetService widgetService) {
    Assert.notNull(widgetService, "widgetService must not be null");
    this.widgetService = widgetService;
  }
  //Get list of all the Widgets in the system
  @GetMapping
  public ResponseEntity<List<Widget>> getAllWidgets() {
    return ResponseEntity.ok(widgetService.getAllWidgets());
  }

  //Create new widgets
  @PostMapping()
  public ResponseEntity<Widget> createWidget(@Valid @RequestBody Widget widget) {
    return ResponseEntity.ok(widgetService.create(widget));
  }

  // Retrieve details of any widget by its name
  @GetMapping("/{name}")
  public ResponseEntity<Widget> getWidget(@PathVariable String name) {
    return ResponseEntity.ok(widgetService.getWidgetByName(name));
  }

  // Update a Widget’s description or price
  @PutMapping("/update")
  public ResponseEntity<Widget> updateWidget(@Valid @RequestBody Widget widget) {
    return ResponseEntity.ok(widgetService.updateWidget(widget));
  }

  //delete a widget
  @DeleteMapping("/{name}")
  public void deleteWidget(@PathVariable String name) {
    widgetService.deleteWidgetByName(name);
  }
}
