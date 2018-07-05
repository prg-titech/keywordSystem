package keywordSystem;

//import java.util.Collection;
import java.util.Vector;

public abstract class Generator {
	static Vector<MaxExpression> allMaxExpression = new Vector<MaxExpression>();

	static Vector<Expression> generate_exact(int depth, Type type, String keywords) {
		initAllMaxExpression(depth, keywords);
		Vector<Expression> result = new Vector<Expression>();
		if (depth != 0) {
			result = getMaxExpressions(depth, type);
		}
		return result;
	}
	// initialize allMaxExpression by adding all max Expression under depth
	private static void initAllMaxExpression(int depth, String keywords) {
		for(int i=1 ; i <= depth ; i++) {
			addAllMaxExpression(allMaxExpression, depth, keywords);
		}
		
	}

	private static void addAllMaxExpression(Vector<MaxExpression> allMaxExpression, int depth, String keywords) {
		for(Type t : new Type().getAllType()) {
			Vector<Expression> maxExpsWithTypeT = new Vector<Expression>();
			for(Generator g : Generator.allExpressionGeneratorsWithTypeT(t)) {
				g.generate_exact_a(depth, maxExpsWithTypeT, keywords);
			}
			selectMaxVarExpressions(maxExpsWithTypeT, keywords);
			allMaxExpression.add(new MaxExpression(depth,t,maxExpsWithTypeT));
		}
		
	}

	// record the number of expressions in each depth ;; modified later
	public static Vector<Expression> getMaxExpressions(int depth, Type type) {
		Vector<Expression> maxExpressions = new Vector<Expression>();
		if (depth == 0) {
		}else {
			for(MaxExpression maxExp : allMaxExpression) {
				if (new Type().matchSubtype(type,maxExp.type) && maxExp.depth == depth) {
					maxExpressions.addAll(maxExp.expression);
				}
			}
		}
		return maxExpressions;

	}

	private Vector<Expression> generateMaxExps_exact(int depth, Type type, String keywords) {
		Vector<Expression> maxExpressions = new Vector<Expression>();
		maxExpressions = getMaxExpressions(depth, type);
		return maxExpressions;
	}

	private Vector<Expression> generateMaxExps_with_depth_or_shallower(int depth, Type type, String keywords) {
		Vector<Expression> result = new Vector<Expression>();
		for (int i = 1; i <= depth; i++) {
			result.addAll(getMaxExpressions(i, type));
		}
		selectMaxVarExpressions(result,keywords);
		return result;
	}

	// generate expressions at depth with type_I 
	protected void generate_exact_a(int depth, Vector<Expression> result, String keywords) {
		if (arity() == 0 && depth == 1) {
			generateWithSubExps(new Expression[0], result, keywords);
		} else {
			generate_with_arity_expression(depth,result,keywords);
		}
		selectMaxVarExpressions(result,keywords);
	}

	private void generate_with_arity_expression(int depth, Vector<Expression> result,
			String keywords) {
		int arity = this.arity();
		for (int exactFlags = 1; exactFlags <= (1 << this.arity()) - 1; exactFlags++) {
			Expression[] subExps = new Expression[arity];
			generate_exact_a_at(depth, exactFlags, arity, subExps, result, keywords);
		}
	}

	private void generate_exact_a_at(int depth, int exactFlags, int rank, Expression[] subExps,
			Vector<Expression> result, String keywords) {
		if (rank == 0) {
			// all generated, use subExps
			generateWithSubExps(subExps, result, keywords);
		} else {
			Vector<Expression> candidates = isBitOn(exactFlags, rank - 1) ?
			/*
			 * exactFlag = 1 , 2 , 3 の場合 つまり depth = 2; exactFlag rank-1 01 10 ==> 00 ==>
			 * false 01 01 ==> 01 ==> true 10 10 ==> 10 ==> true 10 01 ==> 00 ==> false 11
			 * 10 ==> 10 ==> true 11 01 ==> 01 ==> true
			 */

					generateMaxExps_exact(depth - 1,this.types()[rank-1], keywords)
					: generateMaxExps_with_depth_or_shallower(depth - 2, this.types()[rank-1], keywords);

			// generate subexpression at rank
			for (Expression e : candidates) {
				subExps[rank - 1] = e;
				generate_exact_a_at(depth, exactFlags, rank - 1, subExps, result, keywords);

			}

		}
	}

	abstract void generateWithSubExps(Expression[] subExps, Vector<Expression> result, String keywords);

	private boolean isBitOn(int x, int i) {
		return (x & (1 << i)) != 0;
	}

	abstract int arity();
	abstract Type[] types();
/*
 * 引数を加えて、型ごとに違うのgenerator集合を返す。
 */
	static Vector<Generator> allExpressionGeneratorsWithTypeT(Type t) {
		Vector<Generator> allGenerator_typeT = new Vector<Generator>();
			allGenerator_typeT.add(new IntGenerator().getIntGenerator(t));
			allGenerator_typeT.add(new VarGenerator().getVarGenerator(t));
			// addBinaryGenerator(allGenerator);
			addMethodInvocationGenerator(allGenerator_typeT,t);

		return allGenerator_typeT;
	}

	private static void addMethodInvocationGenerator(Vector<Generator> allGenerator_typeT,Type t) {
		for (MethodName methodName : MethodName.allMethodName()) {
			if(methodName.matchReceiverType(t)) {
				MethodInvocationGenerator methodInvGenerator = new MethodInvocationGenerator();
				methodInvGenerator.setName(methodName);
				allGenerator_typeT.add(methodInvGenerator);	
			}
		}
	}

	private static void addBinaryGenerator(Vector<Generator> allGenerator,Type t) {
		for (Operator operator : Operator.allOperator()) {
			BinaryOpGenerator binaryOpGenerator = new BinaryOpGenerator();
			binaryOpGenerator.setOperator(operator);
			allGenerator.add(binaryOpGenerator);
		}
	}

	public static void selectMaxVarExpressions(Vector<Expression> result, String keywords) {
		if(result.size()>0) {
			Vector<Expression> temp = new Vector<Expression>();
			temp.add(result.get(0));
			int size_result = result.size();
			float scoreMax = result.get(0).getScore(keywords);
			for (int i = 1; i < size_result; i++) {
				Expression resultI = result.get(i);
				float scoreI = resultI.getScore(keywords);
				if (scoreI > scoreMax) {
					temp.clear();
					temp.add(resultI);
					scoreMax = scoreI;
				} else {
					if (scoreI == scoreMax) {
						temp.add(resultI);
					}
				}
			}
			result.clear();
			result.addAll(temp);
		}

	}

}

class MaxExpression {
	int depth;
	Type type;
	Vector<Expression> expression;

	public MaxExpression() {

	}

	public MaxExpression(int depth, Type type, Vector<Expression> expression) {
		this.depth = depth;
		this.type = type;
		this.expression = expression;
	}


}
