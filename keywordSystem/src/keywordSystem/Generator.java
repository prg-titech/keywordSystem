package keywordSystem;

//import java.util.Collection;
import java.util.Vector;

public abstract class Generator {
	static Vector<Expression> generate_exact(int depth) {
		if (depth==0) {
			return new Vector<Expression>();
		} else {
			Vector<Expression> result = new Vector<Expression>();
			for (Generator g : allExpressionTypeGenerators()) {
				g.generate_exact_a(depth, result);
			}	
			return result;
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

	private static  Vector<Generator> allExpressionTypeGenerators() {
		Vector<Generator> allGenerator = new Vector<Generator>();
		allGenerator.add(new VarGenerator());
		allGenerator.add(new IntGenerator());
		allGenerator.add(new BinaryOpGenerator());
		for(MethodName methodName : MethodName.allMethodName()) {
			MethodInvocationGenerator methodInvGenerator = new MethodInvocationGenerator();
			methodInvGenerator.setName(methodName);
			methodInvGenerator.setArity(methodName.types.length);
			allGenerator.add(methodInvGenerator);
		}
		/*
		 * allGenerator.add(bestRoot(t,i));
		 */
		return allGenerator;
	}
	
	
}
