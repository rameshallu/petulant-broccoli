package org.sonar.samples.java.checks;

import java.util.List;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

import com.google.common.collect.ImmutableList;

@Rule(key = "OftenMisusedAuthentication",
name = "Often Misused: Authentication",
description = "Attackers can spoof DNS entries. Do not rely on DNS names for security.",
tags = {"API Abuse"})
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.ARCHITECTURE_CHANGEABILITY)
@SqaleConstantRemediation("10min")
public class OftenMisusedAuthentication extends IssuableSubscriptionVisitor {

	@Override
	public List<Kind> nodesToVisit() {
		return ImmutableList.of(Tree.Kind.METHOD_INVOCATION);
	}
	
	@Override
	public void visitNode(Tree tree) {
		MethodInvocationTree methodInvocationTree = (MethodInvocationTree) tree;
		String methodName = methodInvocationTree.symbol().name();
		if ("getCanonicalHostName".equals(methodName) || "getHostName".equals(methodName)) {
			reportIssue(methodInvocationTree, "Often Misused: Authentication");
		}
	}
}
