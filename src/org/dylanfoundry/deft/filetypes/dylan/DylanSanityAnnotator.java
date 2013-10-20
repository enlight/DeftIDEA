/*
 * Copyright 2013, Bruce Mitchener, Jr.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dylanfoundry.deft.filetypes.dylan;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.dylanfoundry.deft.filetypes.dylan.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DylanSanityAnnotator implements Annotator {
  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    if (element instanceof DylanDefinitionClassDefiner) {
      DylanDefinitionClassDefiner cls = (DylanDefinitionClassDefiner) element;
      validateClass(cls, holder);
    } else if (element instanceof  DylanDefinitionConstantDefiner) {
      DylanDefinitionConstantDefiner constant = (DylanDefinitionConstantDefiner) element;
      validateConstant(constant, holder);
    } else if (element instanceof DylanDefinitionFunctionDefiner) {
      DylanDefinitionFunctionDefiner function = (DylanDefinitionFunctionDefiner) element;
      validateFunction(function, holder);
    } else if (element instanceof DylanDefinitionMethodDefiner) {
      DylanDefinitionMethodDefiner method = (DylanDefinitionMethodDefiner) element;
      validateMethod(method, holder);
    } else if (element instanceof  DylanDefinitionVariableDefiner) {
      DylanDefinitionVariableDefiner variable = (DylanDefinitionVariableDefiner) element;
      validateVariable(variable, holder);
    }
  }

  private void validateClass(@NotNull DylanDefinitionClassDefiner cls, @NotNull AnnotationHolder holder) {
    PsiElement tail = cls.getClassDefinitionTail();
    validateDefinitionTail(tail, cls.getVariableName(), holder);
    validateClassName(cls.getVariableName(), holder);
    validateModifiers(cls.getModifiers(), CLASS_MODIFIERS, holder);
  }

  private void validateConstant(@NotNull DylanDefinitionConstantDefiner constant, @NotNull AnnotationHolder holder) {
    validateModifiers(constant.getModifiers(), CONSTANT_MODIFIERS, holder);
    validateInlineModifiers(constant.getModifiers(), holder);
  }

  private void validateFunction(@NotNull DylanDefinitionFunctionDefiner function, @NotNull AnnotationHolder holder) {
    PsiElement tail = function.getFunctionDefinitionTail();
    validateDefinitionTail(tail, function.getVariableName(), holder);
    validateModifiers(function.getModifiers(), FUNCTION_MODIFIERS, holder);
    validateInlineModifiers(function.getModifiers(), holder);
  }

  private void validateMethod(@NotNull DylanDefinitionMethodDefiner method, @NotNull AnnotationHolder holder) {
    PsiElement tail = method.getMethodDefinitionTail();
    validateDefinitionTail(tail, method.getVariableName(), holder);
    validateModifiers(method.getModifiers(), FUNCTION_MODIFIERS, holder);
    validateInlineModifiers(method.getModifiers(), holder);
  }

  private void validateVariable(@NotNull DylanDefinitionVariableDefiner variable, @NotNull AnnotationHolder holder) {
    validateModifiers(variable.getModifiers(), VARIABLE_MODIFIERS, holder);
    if (variable.getVariable() != null) {
      DylanVariable var = variable.getVariable();
      validateVariableName(var.getVariableName(), holder);
    } else {
      for (DylanVariable var : variable.getVariableList().getVariableList()) {
        validateVariableName(var.getVariableName(), holder);
      }
    }
  }

  private void validateDefinitionTail(PsiElement tail, PsiElement variableName, @NotNull AnnotationHolder holder) {
    if (tail.getChildren().length > 0) {
      PsiElement tailName = tail.getChildren()[0];
      String tailNameText = tailName.getText().trim().toLowerCase();
      String nameText = variableName.getText().trim().toLowerCase();
      if (!tailNameText.equals(nameText)) {
        holder.createErrorAnnotation(tailName, "Definer end has wrong name, expected '" + variableName.getText() + "'.");
      }
    }
  }

  private void validateClassName(PsiElement className, @NotNull AnnotationHolder holder) {
    String name = className.getText();
    if (!name.startsWith("<") || !name.endsWith(">")) {
      holder.createWarningAnnotation(className, "Class names usually begin with '<' and end with '>'.");
    }
  }

  private void validateVariableName(PsiElement variableName, @NotNull AnnotationHolder holder) {
    String name = variableName.getText();
    if (!name.startsWith("*") || !name.endsWith("*")) {
      holder.createWarningAnnotation(variableName, "Variable names usually begin with '*' and end with '*'.");
    }
  }

  private void validateModifiers(DylanModifiers modifiers, Set<String> validModifiers, @NotNull AnnotationHolder holder) {
    if ((modifiers == null) || modifiers.getModifierList() == null) {
      return;
    }
    for (DylanModifier modifier : modifiers.getModifierList()) {
      if (!validModifiers.contains(modifier.getText().trim().toLowerCase())) {
        holder.createErrorAnnotation(modifier, "'" + modifier.getText() + "' is not a valid modifier for this definer.");
      }
    }
  }

  private void validateInlineModifiers(DylanModifiers modifiers, @NotNull AnnotationHolder holder) {
    if ((modifiers == null) || modifiers.getModifierList() == null) {
      return;
    }
    boolean hasInlinePolicy = false;
    for (DylanModifier modifier : modifiers.getModifierList()) {
      if (INLINE_POLICIES.contains(modifier.getText().trim().toLowerCase())) {
        if (hasInlinePolicy) {
          holder.createErrorAnnotation(modifier, "This definer already has an inline policy specified.");
        }
        hasInlinePolicy = true;
      }
    }
  }

  private final static Set<String> CLASS_MODIFIERS = new HashSet<String>(Arrays.asList(
          "abstract",
          "compiler-open",
          "concrete",
          "dynamic",
          "free",
          "open",
          "primary",
          "sealed"
  ));
  private final static Set<String> CONSTANT_MODIFIERS = new HashSet<String>(Arrays.asList(
          "default-inline",
          "inline",
          "inline-only",
          "may-inline",
          "not-inline"
  ));
  private final static Set<String> FUNCTION_MODIFIERS = new HashSet<String>(Arrays.asList(
          "compiler-open",
          "compiler-sideways",
          "default-inline",
          "dynamic",
          "inline",
          "inline-only",
          "may-inline",
          "not-inline",
          "open",
          "sealed"
  ));
  private final static Set<String> VARIABLE_MODIFIERS = new HashSet<String>(Arrays.asList(
          "thread"
  ));
  private final static Set<String> INLINE_POLICIES = new HashSet<String>(Arrays.asList(
          "default-inline",
          "inline",
          "inline-only",
          "may-inline",
          "not-inline"
  ));
}
