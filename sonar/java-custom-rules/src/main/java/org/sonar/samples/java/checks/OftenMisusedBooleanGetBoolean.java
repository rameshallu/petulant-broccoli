package org.sonar.samples.java.checks;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

@Rule(key = "OftenMisusedBooleanGetBoolean",
name = "Often Misused: Boolean.getBoolean()",
description = "The method Boolean.getBoolean() is often confused with Boolean.valueOf() or Boolean.parseBoolean() method calls.",
tags = {"API Abuse"})
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.ARCHITECTURE_CHANGEABILITY)
@SqaleConstantRemediation("10min")
public class OftenMisusedBooleanGetBoolean extends BaseTreeVisitor implements JavaFileScanner {

	private JavaFileScannerContext context;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
	    scan(context.getTree());
	}

	@Override
	public void visitMemberSelectExpression(MemberSelectExpressionTree tree) {
		if (tree.parent().is(Kind.METHOD_INVOCATION)) {
			MethodInvocationTree methodInvocationTree = (MethodInvocationTree) tree.parent();
			if (tree.expression().is(Kind.IDENTIFIER) && "getBoolean".equals(tree.identifier().name()) && matchArgument(methodInvocationTree.arguments())) {
				IdentifierTree identifierTree = (IdentifierTree) tree.expression();
				if("Boolean".equals(identifierTree.name())) {
					context.reportIssue(this, tree, "Often Misused: Boolean.getBoolean(String)");
				}
			}
		}
	}

	private static boolean matchArgument(Arguments args) {
		return args.size() == 1 && "String".equals(args.get(0).symbolType().name());
	}
}
