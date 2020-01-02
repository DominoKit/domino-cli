package org.dominokit.cli.structure.files;

import java.lang.Override;
import org.dominokit.jacksonapt.AbstractObjectMapper;
import org.dominokit.jacksonapt.JsonDeserializer;
import org.dominokit.jacksonapt.JsonSerializer;

public final class TemplateFile_MapperImpl extends AbstractObjectMapper<TemplateFile> {
  public static final TemplateFile_MapperImpl INSTANCE = new TemplateFile_MapperImpl();

  public TemplateFile_MapperImpl() {
    super("TemplateFile");
  }

  @Override
  protected JsonDeserializer<TemplateFile> newDeserializer() {
    return new TemplateFileBeanJsonDeserializerImpl();
  }

  @Override
  protected JsonSerializer<?> newSerializer() {
    return new TemplateFileBeanJsonSerializerImpl();
  }
}
