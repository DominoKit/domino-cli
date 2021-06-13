package org.dominokit.cli.structure.files;

import java.lang.Override;
import org.dominokit.jackson.AbstractObjectMapper;
import org.dominokit.jackson.JsonDeserializer;
import org.dominokit.jackson.JsonSerializer;

public final class TemplateFile_MapperImpl extends AbstractObjectMapper<TemplateFile> {
  public static final TemplateFile_MapperImpl INSTANCE = new TemplateFile_MapperImpl();

  public TemplateFile_MapperImpl() {
    super("TemplateFile");
  }

  @Override
  protected JsonDeserializer<TemplateFile> newDeserializer() {
    return TemplateFileBeanJsonDeserializerImpl.getInstance();
  }

  @Override
  protected JsonSerializer<?> newSerializer() {
    return TemplateFileBeanJsonSerializerImpl.getInstance();
  }
}
