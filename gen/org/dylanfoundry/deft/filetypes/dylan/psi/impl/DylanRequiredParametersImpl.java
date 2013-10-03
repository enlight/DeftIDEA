// This is a generated file. Not intended for manual editing.
package org.dylanfoundry.deft.filetypes.dylan.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.dylanfoundry.deft.filetypes.dylan.psi.DylanTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.dylanfoundry.deft.filetypes.dylan.psi.*;

public class DylanRequiredParametersImpl extends ASTWrapperPsiElement implements DylanRequiredParameters {

  public DylanRequiredParametersImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public DylanRequiredParameter getRequiredParameter() {
    return findNotNullChildByClass(DylanRequiredParameter.class);
  }

  @Override
  @Nullable
  public DylanRequiredParameters getRequiredParameters() {
    return findChildByClass(DylanRequiredParameters.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DylanVisitor) ((DylanVisitor)visitor).visitRequiredParameters(this);
    else super.accept(visitor);
  }

}
