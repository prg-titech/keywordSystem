package keywordSystem;

//import java.util.Collection;
import java.util.Vector;

public abstract class Generator {
	static Vector<Expression> generate_exact(int depth) {
		if (depth==0) {
			return new Vector<Expression>();
		} else {
			Vector<Expression> result = new Vector<Expression>();
			for (Generator g : allExpressionTypeGeneratorsBesideMethodInvocation()) {
				g.generate_exact_a(depth, result);
			}
			
			expressionFromMethodInvocationGenerator(depth, result);	
			return result;
		}
	}
	
	private static void expressionFromMethodInvocationGenerator(int depth, Vector<Expression> result) {
		for(MethodName methodName : MethodName.allMethodName()) {
			MethodInvocationGenerator methodInvGenerator = new MethodInvocationGenerator();
			methodInvGenerator.setName(methodName.name);
			methodInvGenerator.setArity(methodName.types.length);
			methodInvGenerator.generate_exact_a(depth, result);
		}
	}

	private Vector<Expression> generate_with_depth_or_shallower(int depth) {
		Vector<Expression> result = new Vector<Expression>();
		for (int i=0; i<=depth; i++) {
			result.addAll(generate_exact(i));
		}
		return result;
	}
	protected void generate_exact_a(int depth, Vector<Expression> result) {
		if (arity()==0 && depth==1) {
			generateWithSubExps(new Expression[0], result);
		} else {
			generate_with_arity_expression(depth,this.arity(),result);
		}
	}

	private void generate_with_arity_expression(int depth,int arity,Vector<Expression> result) {
		for (int exactFlags=1; exactFlags <= (1<<this.arity())-1; exactFlags++) {
			Expression[] subExps = new Expression[arity];
			generate_exact_a_at(depth, exactFlags, this.arity(), subExps, result);
		}
	}
	private void generate_exact_a_at(int depth, int exactFlags, int rank, Expression[] subExps, Vector<Expression> result) {
		if(rank==0) {
			// all generated, use subExps
			generateWithSubExps(subExps, result);
		} else {
			Vector<Expression> candidates = isBitOn(exactFlags,rank-1) ? 
					generate_exact(depth-1) :
						generate_with_depth_or_shallower(depth-2);
			
			// generate subexpression at rank
			for (Expression e : candidates) {
				subExps[rank-1] = e;
				generate_exact_a_at(depth, exactFlags, rank-1, subExps, result);
					
			} 
			
		}
	}


	abstract void generateWithSubExps(Expression[] subExps, Vector<Expression> result);

	private boolean isBitOn(int x, int i) {
		return (x & (1<<i))!=0;
	}

	abstract int arity();

	private static  Generator[] allExpressionTypeGeneratorsBesideMethodInvocation() {
		return new Generator[]{new VarGenerator(),new IntGenerator()
				 , new BinaryOpGenerator()
				};
	}
}
