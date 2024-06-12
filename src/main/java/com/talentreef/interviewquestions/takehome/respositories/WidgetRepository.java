package com.talentreef.interviewquestions.takehome.respositories;

import com.talentreef.interviewquestions.takehome.models.Widget;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class WidgetRepository {

  private List<Widget> table = new ArrayList<>();
  private final AtomicLong idCounter = new AtomicLong(1);


  public void deleteByName(String name) {
    this.table = table.stream()
                      .filter((Widget widget) -> !name.equals(widget.getName()))
                      .collect(Collectors.toCollection(ArrayList::new));
  }
  public void deleteById(Long id) {
    this.table = table.stream()
            .filter((Widget widget) -> !id.equals(widget.getId()))
            .collect(Collectors.toCollection(ArrayList::new));
  }

  public List<Widget> findAll() {
    return table;
  }

  public Optional<Widget> findByName(String name) {
      return table.stream().filter((Widget widget) -> name.equals(widget.getName()))
                                   .findAny();
  }

  public Widget save(Widget widget) {
    // Check if the name is already used
    Optional<Widget> validationWidget = findByName(widget.getName());
    if (validationWidget.isPresent()) {
      throw new IllegalArgumentException("Widget with name '" + widget.getName() + "' already exists");
    }

    if (widget.getId() == null) {
      widget.setId(idCounter.getAndIncrement());
    }
    deleteByName(widget.getName());
    table.add(widget);
    return widget;
  }

  public Optional<Widget> getByName(String name) {
    return table.stream().filter((Widget w) -> name.equals(w.getName())).findFirst();
  }
  public Widget update(Widget widget) {
    Widget updatedWidget = findByName(widget.getName()).get();
    if (widget.getDescription() != null) {
      updatedWidget.setDescription(widget.getDescription());
    }
    if (widget.getPrice() != null) {
      updatedWidget.setPrice(widget.getPrice());

    }
    deleteByName(widget.getName());
    table.add(updatedWidget);
    return updatedWidget;
  }

}
