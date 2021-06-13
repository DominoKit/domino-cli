package org.dominokit.cli.structure.folders;

import java.lang.Override;
import org.dominokit.jackson.AbstractObjectMapper;
import org.dominokit.jackson.JsonDeserializer;
import org.dominokit.jackson.JsonSerializer;

public final class Folder_MapperImpl extends AbstractObjectMapper<Folder> {
  public static final Folder_MapperImpl INSTANCE = new Folder_MapperImpl();

  public Folder_MapperImpl() {
    super("Folder");
  }

  @Override
  protected JsonDeserializer<Folder> newDeserializer() {
    return FolderBeanJsonDeserializerImpl.getInstance();
  }

  @Override
  protected JsonSerializer<?> newSerializer() {
    return FolderBeanJsonSerializerImpl.getInstance();
  }
}
