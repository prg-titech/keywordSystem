package keywordSystem;

//import java.util.Collection;
import java.util.Vector;

public abstract class Generator {
	static Vector<MaxExpression> allMaxExpression = new Vector<MaxExpression>();

	static Vector<Expression> generate_exact(int depth, Type type, String keywords) {
		new MaxExpression().addAllMaxExps_with_depth_or_shallow(allMaxExpression, depth, keywords);
		if (depth == 0) {
			return new Vector<Expression>();
		} else {
			Vector<Expression> result = new Vector<Expression>();
			result = getMaxExpressions(depth, type);
			return result;
		}
	}
	
	// record the number of expressions in each depth ;; modified later
	public static Vector<Expression> getMaxExpressions(int depth, Type type) {
		Vector<Expression> maxExpressions = new Vector<Expression>();
		int size_maxExps = allMaxExpression.size();
		for (int i = 0; i < size_maxExps; i++) {
			MaxExpression maxExp_I = allMaxExpression.get(i);
			Type maxExpType_I = maxExp_I.type;
			int maxExpDepth_I = maxExp_I.depth;
			Vector<Expression> maxExpression_I = maxExp_I.expression;
			if (new Type().matchSubtype(type, maxExpType_I) && maxExpDepth_I == depth) {
				maxExpressions.addAll(maxExpression_I);
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
		for (int i = 0; i <= depth; i++) {
			result.addAll(getMaxExpressions(i, type));
		}
		return result;
	}

	// generate expressions at depth with type_I which has highest score according to keywords
	protected void generate_exact_a(int depth, Vector<Expression> result, Type type, String keywords) {
		if (arity() == 0 && depth == 1) {
			generateWithSubExps(new Expression[0], result, type, keywords);
		} else {
			generateAllExpsWithTypeInDepth(result,depth,type,keywords);
			selectMaxVarExpressions(result,keywords);
		}
	}
	// Method 1 & Method 2
	private void generateAllExpsWithTypeInDepth(Vector<Expression> result, int depth, Type type, String keywords) {
		
		
	}

	private void generate_with_arity_expression(int depth, int arity, Vector<Expression> result, Type type,
			String keywords) {
		for (int exactFlags = 1; exactFlags <= (1 << this.arity()) - 1; exactFlags++) {
			Expression[] subExps = new Expression[arity];
			generate_exact_a_at(depth, exactFlags, this.arity(), subExps, result, type, keywords);
		}
	}

	private void generate_exact_a_at(int depth, int exactFlags, int rank, Expression[] subExps,
			Vector<Expression> result, Type type, String keywords) {
		if (rank == 0) {
			// all generated, use subExps
			generateWithSubExps(subExps, result, type, keywords);
		} else {
			Vector<Expression> candidates = isBitOn(exactFlags, rank - 1) ?
			/*
			 * exactFlag = 1 , 2 , 3 の場合 つまり depth = 2; exactFlag rank-1 01 10 ==> 00 ==>
			 * false 01 01 ==> 01 ==> true 10 10 ==> 10 ==> true 10 01 ==> 00 ==> false 11
			 * 10 ==> 10 ==> true 11 01 ==> 01 ==> true
			 */

					generateMaxExps_exact(depth - 1, type, keywords)
					: generateMaxExps_with_depth_or_shallower(depth - 2, type, keywords);

			// generate subexpression at rank
			for (Expression e : candidates) {
				subExps[rank - 1] = e;
				generate_exact_a_at(depth, exactFlags, rank - 1, subExps, result, type, keywords);

			}

		}
	}

	abstract void generateWithSubExps(Expression[] subExps, Vector<Expression> result, Type type, String keywords);

	private boolean isBitOn(int x, int i) {
		return (x & (1 << i)) != 0;
	}

	abstract int arity();
/*
 * 引数を加えて、型ごとに違うのgenerator集合を返す。
 */
	static Vector<Generator> allExpressionTypeGenerators() {
		Vector<Generator> allGenerator = new Vector<Generator>();
		allGenerator.add(new IntGenerator());
		allGenerator.add(new VarGenerator());
//		allGenerator.add(new MethodInvocationGenerator());
		// addBinaryGenerator(allGenerator);
//		 addMethodInvocationGenerator(allGenerator);

		return allGenerator;
	}

	private static void addMethodInvocationGenerator(Vector<Generator> allGenerator) {
		for (MethodName methodName : MethodName.allMethodName()) {
			MethodInvocationGenerator methodInvGenerator = new MethodInvocationGenerator();
			methodInvGenerator.setName(methodName);
			allGenerator.add(methodInvGenerator);
		}
	}

	private static void addBinaryGenerator(Vector<Generator> allGenerator) {
		for (Operator operator : Operator.allOperator()) {
			BinaryOpGenerator binaryOpGenerator = new BinaryOpGenerator();
			binaryOpGenerator.setOperator(operator);
			allGenerator.add(binaryOpGenerator);
		}
	}

	public void selectMaxVarExpressions(Vector<Expression> result, String keywords) {
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

	void addAllMaxExpression(Vector<MaxExpression> allMaxExpression, int depth, String keywords) {
		int size_allType = Type.allType.size();
		for (int i = 0; i < size_allType; i++) {
			Type type_I = Type.allType.get(i);
			Vector<Expression> exps_typeI = new Vector<Expression>();
			for (Generator g : Generator.allExpressionTypeGenerators()) {
				// generate expressions at depth with type_I which has highest score according to keywords
				g.generate_exact_a(depth, exps_typeI, type_I, keywords);
			}
			MaxExpression maxExps_typeI = new MaxExpression(depth, type_I, exps_typeI);
			allMaxExpression.add(maxExps_typeI);
		}

	}
	
	void addAllMaxExps_with_depth_or_shallow(Vector<MaxExpression> allMaxExpression_under_depth, int depth, String keywords) {
		for(int i=0 ; i <= depth ; i++) {
			this.addAllMaxExpression(allMaxExpression_under_depth, depth, keywords);
		}
	}

}
