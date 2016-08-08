package org.sonar.samples.java.checks;

import java.util.List;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.IfStatementTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import com.google.common.collect.ImmutableList;

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
			reportIssue(methodInvocationTree, "message");
		}
	}
}
