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

public class DylanDefinitionMacroCallImpl extends ASTWrapperPsiElement implements DylanDefinitionMacroCall {

  public DylanDefinitionMacroCallImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public DylanDefineBodyWord getDefineBodyWord() {
    return findChildByClass(DylanDefineBodyWord.class);
  }

  @Override
  @Nullable
  public DylanDefineListWord getDefineListWord() {
    return findChildByClass(DylanDefineListWord.class);
  }

  @Override
  @Nullable
  public DylanDefinitionTail getDefinitionTail() {
    return findChildByClass(DylanDefinitionTail.class);
  }

  @Override
  @Nullable
  public PsiElement getBodyFragmentOpt() {
    return findChildByType(BODY_FRAGMENT_OPT);
  }

  @Override
  @Nullable
  public PsiElement getListFragmentOpt() {
    return findChildByType(LIST_FRAGMENT_OPT);
  }

  @Override
  @Nullable
  public PsiElement getModifiersOpt() {
    return findChildByType(MODIFIERS_OPT);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DylanVisitor) ((DylanVisitor)visitor).visitDefinitionMacroCall(this);
    else super.accept(visitor);
  }

}
