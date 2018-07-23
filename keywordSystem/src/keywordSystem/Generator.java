package keywordSystem;

//import java.util.Collection;
import java.util.Vector;

public abstract class Generator {
	public static Vector<MaxExpression> allMaxExpression = new Vector<MaxExpression>();
	abstract Type[] types();
	abstract void addGenerator(Type t, Vector<Generator> allGeneratorWithTypeT);
	
	public static Vector<Expression> generateExact(int depth, Type type, String keywords) {
		Vector<Expression> result = new Vector<Expression>();		
		initAllMaxExpression(depth, keywords);
		if (depth != 0) {
			result = getMaxExpressionsExactAtDepth(depth, type);
		}
		return result;
	}
	// initialize allMaxExpression by adding all max Expression under depth
	private static void initAllMaxExpression(int depth, String keywords) {
		for(int i=1 ; i <= depth ; i++) {
			addAllMaxExpression(i,keywords);
		}
		
	}
	// add all max expressions to the table in each depth according to the keywords
	private static void addAllMaxExpression(int depth, String keywords) {
		Vector<MaxExpression> allMaxExpInDepth = new Vector<MaxExpression>();
		for(Type t : new Type().getAllType()) {
			Vector<Expression> maxExpsWithTypeT = new Vector<Expression>();
			for(Generator g : Generator.allExpressionGeneratorsWithTypeT(t)) {
				g.generateExactAtDepth(depth, maxExpsWithTypeT);
			}
	
//			System.out.println("Depth : "+ depth + " Type : " + t.toString() + "  size : " + maxExpsWithTypeT.size());
//			maxExpsWithTypeT.stream().forEach(System.out::println);
//			System.out.println("================================");
			
			selectMaxExpressions(maxExpsWithTypeT, keywords);
			allMaxExpInDepth.add(new MaxExpression(depth,t,maxExpsWithTypeT));
		}
		Generator.allMaxExpression.addAll(allMaxExpInDepth);

	}

	// record the number of expressions in each depth ;; modified later
	// or this should use set instead of vector
	private static Vector<Expression> getMaxExpressionsExactAtDepth(int depth, Type type) {
		Vector<Expression> maxExpressions = new Vector<Expression>();
		if (depth == 0) {
		}else {
			for(MaxExpression maxExp : allMaxExpression) {
				if (maxExp.getType().equals(type) && maxExp.getDepth() == depth) {
					maxExpressions.addAll(maxExp.getExpression());
				}
			}
		}
		return maxExpressions;

	}


	private Vector<Expression> getMaxExpressionsLeqDepth(int depth, Type type) {
		Vector<Expression> result = new Vector<Expression>();
		for (int i = 1; i <= depth; i++) {
			result.addAll(getMaxExpressionsExactAtDepth(i, type));
		}
		return result;
	}

	// generate expressions at depth
	protected void generateExactAtDepth(int depth, Vector<Expression> result) {
		if (this.types().length == 0 && depth == 1) {
			generateWithSubExps(new Expression[0], result);
		} else {
			generateWithArityExpression(depth,result);
		}
	}

	private void generateWithArityExpression(int depth, Vector<Expression> result) {
		int arity = this.types().length;
		for (int exactFlags = 1; exactFlags <= (1 << arity) - 1; exactFlags++) {
			Expression[] subExps = new Expression[arity];
			generateWithSubExpsExactAtDepth(depth, exactFlags, arity, subExps, result);
		}
	}

	private void generateWithSubExpsExactAtDepth(int depth, int exactFlags, int rank, Expression[] subExps,
			Vector<Expression> result) {
		if (rank == 0) {
			// all generated, use subExps
			generateWithSubExps(subExps, result);
		} else {
			Vector<Expression> candidates = isBitOn(exactFlags, rank - 1) ?
			/*
			 * exactFlag = 1 , 2 , 3 の場合 つまり depth = 2; exactFlag rank-1 01 10 ==> 00 ==>
			 * false 01 01 ==> 01 ==> true 10 10 ==> 10 ==> true 10 01 ==> 00 ==> false 11
			 * 10 ==> 10 ==> true 11 01 ==> 01 ==> true
			 */

					getMaxExpressionsExactAtDepth(depth - 1,this.types()[rank-1])
					: getMaxExpressionsLeqDepth(depth - 2, this.types()[rank-1]);

			// generate subexpression at rank
			for (Expression e : candidates) {
				subExps[rank - 1] = e;
				generateWithSubExpsExactAtDepth(depth, exactFlags, rank - 1, subExps, result);

			}

		}
	}

	private boolean isBitOn(int x, int i) {
		return (x & (1 << i)) != 0;
	}
	abstract void generateWithSubExps(Expression[] subExps, Vector<Expression> result);
/*
 * 引数を加えて、型ごとに違うのgenerator集合を返す。
 */
	private static Vector<Generator> allExpressionGeneratorsWithTypeT(Type t) {
		Vector<Generator> allGenerator_typeT = new Vector<Generator>();
		for(Generator g:Generator.allExpressionGenerator()) {
			g.addGenerator(t,allGenerator_typeT);
		}
		return allGenerator_typeT;
	}
	
	private static Generator[] allExpressionGenerator(){
		return new Generator[] {
				new IntGenerator(),new VarGenerator(),
				new BinaryOpGenerator(),
				new MethodInvocationGenerator()
		};
	}

	public static void selectMaxExpressions(Vector<Expression> result, String keywords) {
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