package org.dominokit.cli.structure.folders;

import java.lang.Override;
import org.dominokit.jacksonapt.AbstractObjectMapper;
import org.dominokit.jacksonapt.JsonDeserializer;
import org.dominokit.jacksonapt.JsonSerializer;

public final class Folder_MapperImpl extends AbstractObjectMapper<Folder> {
  public static final Folder_MapperImpl INSTANCE = new Folder_MapperImpl();

  public Folder_MapperImpl() {
    super("Folder");
  }

  @Override
  protected JsonDeserializer<Folder> newDeserializer() {
    return new FolderBeanJsonDeserializerImpl();
  }

  @Override
  protected JsonSerializer<?> newSerializer() {
    return new FolderBeanJsonSerializerImpl();
  }
}
