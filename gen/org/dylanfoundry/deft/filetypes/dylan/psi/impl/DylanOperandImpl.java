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

public class DylanOperandImpl extends ASTWrapperPsiElement implements DylanOperand {

  public DylanOperandImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public DylanLeaf getLeaf() {
    return findChildByClass(DylanLeaf.class);
  }

  @Override
  @Nullable
  public DylanOperand getOperand() {
    return findChildByClass(DylanOperand.class);
  }

  @Override
  @Nullable
  public DylanOperandTail getOperandTail() {
    return findChildByClass(DylanOperandTail.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DylanVisitor) ((DylanVisitor)visitor).visitOperand(this);
    else super.accept(visitor);
  }

}
