package org.dominokit.cli.model;

import java.lang.Override;
import org.dominokit.jackson.AbstractObjectMapper;
import org.dominokit.jackson.JsonDeserializer;
import org.dominokit.jackson.JsonSerializer;

public final class Project_MapperImpl extends AbstractObjectMapper<Project> {
  public static final Project_MapperImpl INSTANCE = new Project_MapperImpl();

  public Project_MapperImpl() {
    super("Project");
  }

  @Override
  protected JsonDeserializer<Project> newDeserializer() {
    return ProjectBeanJsonDeserializerImpl.getInstance();
  }

  @Override
  protected JsonSerializer<?> newSerializer() {
    return ProjectBeanJsonSerializerImpl.getInstance();
  }
}
