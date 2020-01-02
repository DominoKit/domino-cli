package org.dominokit.cli.model;

import java.lang.Override;
import org.dominokit.jacksonapt.AbstractObjectMapper;
import org.dominokit.jacksonapt.JsonDeserializer;
import org.dominokit.jacksonapt.JsonSerializer;

public final class Project_MapperImpl extends AbstractObjectMapper<Project> {
  public static final Project_MapperImpl INSTANCE = new Project_MapperImpl();

  public Project_MapperImpl() {
    super("Project");
  }

  @Override
  protected JsonDeserializer<Project> newDeserializer() {
    return new ProjectBeanJsonDeserializerImpl();
  }

  @Override
  protected JsonSerializer<?> newSerializer() {
    return new ProjectBeanJsonSerializerImpl();
  }
}
