// This is a generated file. Not intended for manual editing.
package org.dylanfoundry.deft.filetypes.dylan.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DylanIfStatement extends PsiElement {

  @Nullable
  DylanBody getBody();

  @Nullable
  DylanElseStatement getElseStatement();

  @NotNull
  List<DylanElseifStatement> getElseifStatementList();

  @NotNull
  DylanExpression getExpression();

  @NotNull
  DylanIfTail getIfTail();

}