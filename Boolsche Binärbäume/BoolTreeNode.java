/**
 * Ein Objekt der Klasse BoolTreeNode repraesentiert einen booleschen Binaerbaum.
 * Dieser Baum steht fuer eine boolesche Formel ueber Variablen, true und false.
 */
public class BoolTreeNode {
    private String variable;
    private BoolTreeNode child1;
    private BoolTreeNode child2;

    /**
     * Konstruktor fuer einen neuen booleschen Binaerbaum,
     * der eine Variable repraesentiert.
     * @param variableInput die nicht negative Hoehe
     */
    private BoolTreeNode(String variableInput) {
        variable = variableInput;
        child1 = null;
        child2 = null;
    }

    /**
     * Konstruktor fuer einen neuen booleschen Binaerbaum,
     * der die Negation der Repraesentation des uebergebenen Baums repraesentiert.
     * @param negated der zu negierende Binaerbaum
     */
    private BoolTreeNode(BoolTreeNode negated) {
        variable = "";
        child1 = negated;
        child2 = null;
    }

    /**
     * Konstruktor fuer einen neuen booleschen Binaerbaum, der die
     * Konjunktion der Repraesentationen der uebergebenen Baeume repraesentiert.
     * @param conjunct1 der erste zu konjugierende Binaerbaum
     * @param conjunct2 der zweite zu konjugierende Binaerbaum
     */
    private BoolTreeNode(BoolTreeNode conjunct1, BoolTreeNode conjunct2) {
        variable = "";
        child1 = conjunct1;
        child2 = conjunct2;
    }

    /**
     * Erzeugt einen neuen booleschen Binaerbaum, der true repraesentiert.
     * @return den neuen booleschen Binaerbaum, der true repraesentiert
     */
    public static BoolTreeNode boolTreeTrueNode() {
        BoolTreeNode res = new BoolTreeNode("true");
        return res;
    }

    /**
     * Erzeugt einen neuen booleschen Binaerbaum, der false repraesentiert.
     * @return den neuen booleschen Binaerbaum, der false repraesentiert
     */
    public static BoolTreeNode boolTreeFalseNode() {
        BoolTreeNode res = new BoolTreeNode("false");
        return res;
    }

    /**
     * Erzeugt einen neuen booleschen Binaerbaum, der eine Variable repraesentiert.
     * @param variableInput die zu repraesentierende Variable
     * @return den neuen booleschen Binaerbaum, der die Variable repraesentiert
     */
    public static BoolTreeNode boolTreeVariableNode(String variableInput) {
        if (variableInput.equals("true") || variableInput.equals("false")) {
            Utils.error("Variable must not be namend \"true\" or \"false\"");
            return null;
        } else if (variableInput.equals("")) {
            Utils.error("Variable must not have empty string as name");
            return null;            
        } else {
            BoolTreeNode res = new BoolTreeNode(variableInput);
            return res;
        }
    }

    /**
     * Erzeugt einen neuen booleschen Binaerbaum, der eine Negation repraesentiert.
     * @param negated der Binaerbaum der zu negierenden Formel
     * @return den neuen booleschen Binaerbaum, der die Negation repraesentiert
     */
    public static BoolTreeNode boolTreeNotNode(BoolTreeNode negated) {
        if (negated == null) {
            Utils.error("Negation-child is null, new node cannot be created");
            return null;            
        }
        BoolTreeNode notnode = new BoolTreeNode(negated);
        return notnode;
    }

    /**
     * Erzeugt einen neuen booleschen Binaerbaum, der eine Konjunktion repraesentiert.
     * @param conjunct1 der Binaerbaum der ersten zu konjugierenden Formel
     * @param conjunct2 der Binaerbaum der zweiten zu konjugierenden Formel
     * @return den neuen booleschen Binaerbaum, der die Konjunktion repraesentiert
     */
    public static BoolTreeNode boolTreeAndNode(BoolTreeNode conjunct1, BoolTreeNode conjunct2) {
        if (conjunct1 == null || conjunct2 == null) {
            if (conjunct1 != null || conjunct2 != null) {
                Utils.error("One of the conjunction-children is null, new node cannot be created");
                return null;
            } else {
                Utils.error("Both conjunction-children are null, new node cannot be created");
                return null;
            }
        }
        BoolTreeNode andnode = new BoolTreeNode(conjunct1,conjunct2);
        return andnode;
    }
	
	/**
     * Erzeugt einen neuen booleschen Binaerbaum, der eine Disjunktion repraesentiert.
     * @param conjunct1 der Binaerbaum der ersten zu disjungierenden Formel
     * @param conjunct2 der Binaerbaum der zweiten zu disjugierenden Formel
     * @return den neuen booleschen Binaerbaum, der die Disjunktion repraesentiert
     */
	public static BoolTreeNode boolTreeOrNode(BoolTreeNode conjunct1, BoolTreeNode conjunct2) {
        if (conjunct1 == null || conjunct2 == null) {
            if (conjunct1 != null || conjunct2 != null) {
                Utils.error("One of the conjunction-children is null, new node cannot be created");
                return null;
            } else {
                Utils.error("Both conjunction-children are null, new node cannot be created");
                return null;
            }
        }
		BoolTreeNode notConjunct1 = new BoolTreeNode(conjunct1);
		BoolTreeNode notConjunct2 = new BoolTreeNode(conjunct2);
		BoolTreeNode andnode = new BoolTreeNode(notConjunct1, notConjunct2);
		BoolTreeNode ornode = new BoolTreeNode(andnode);
		return ornode;
	}
	
	/**
     * Ermittelt die Tiefe des booleschen Binaerbaums.
     * @return die Tiefe des booleschen Binaerbaums
     */
    public int depth() {
        if (child1 != null) {
            int depth1 = child1.depth();
            if (child2 != null) {
                int depth2 = child2.depth();
                return Utils.max(depth1,depth2) + 1;
            } else {
                return depth1 + 1;
            }
        } else {
            return 0;
        }
    }

    /**
     * Ermittelt, ob der boolesche Binaerbaum ein Blatt ist, also keine Kinder hat.
     * @return true, wenn der Baum ein Blatt ist, sonst false
     */
    public boolean isLeaf() {
        return (child1 == null && child2 == null);
    }

    /**
     * Ermittelt, ob der boolesche Binaerbaum ein Blatt ist, das true repraesentiert.
     * @return true, wenn der Baum ein Blatt ist und true repraesentiert, sonst false
     */
    public boolean isTrueLeaf() {
        return (isLeaf() && variable.equals("true"));
    }

    /**
     * Ermittelt, ob der boolesche Binaerbaum ein Blatt ist, das false repraesentiert.
     * @return true, wenn der Baum ein Blatt ist und false repraesentiert, sonst false
     */
    public boolean isFalseLeaf() {
        return (isLeaf() && variable.equals("false"));
    }

    /**
     * Ermittelt, ob der boolesche Binaerbaum eine Negation repraesentiert.
     * @return true, wenn der Baum eine Negation repraesentiert, sonst false
     */
    public boolean isNegation() {
        return (child1 != null && child2 == null);
    }

    /**
     * Ermittelt, ob der boolesche Binaerbaum eine Konjunktion repraesentiert.
     * @return true, wenn der Baum eine Konjunktion repraesentiert, sonst false
     */
    public boolean isConjunction() {
        return (child1 != null && child2 != null);
    }

    /**
     * Ermittelt den Wahrheitswert der durch den booleschen Binaerbaum bei einer
     * gegebenen Variablenbelegung.
     * @param trueVars genau die Variablen, die zu true evaluiert werden
     * @return true, die repraesentierte Formel zu true evaluiert wird, sonst false
     */
    public boolean evaluate(String... trueVars) {
        if (child1 == null && child2 == null) {
            return Utils.evaluateVariable(variable,trueVars);
        } else if (child1 != null && child2 == null) {
            return !(child1.evaluate(trueVars));
        } else if (child1 != null && child2 != null) {
            return child1.evaluate(trueVars) && child2.evaluate(trueVars);
        } else {
            Utils.error("Should never occur");
            return false;
        }
    }

    /**
     * Entfernt alle Vorkommen von doppelten Negationen aus dem gesamten Baum.
     * @return true, wenn irgendwo eine Veraenderung vorgenommen wurde, sonst false
     */
    public boolean removeDoubleNegations() {
        if (isNegation()) {
            if (child1.isNegation()) {
                child1.child1.removeDoubleNegations();
                child2 = child1.child1.child2;
                variable = child1.child1.variable;
                child1 = child1.child1.child1;
                return true;
            } else {
                return child1.removeDoubleNegations();
            }
        }
        if (isConjunction()) {
            return child1.removeDoubleNegations() | child2.removeDoubleNegations();
        }
        return false;
    }
	
	/**
     * Entfernt alle Vorkommen von negierten Wahrheitswerten aus dem gesamten Baum.
     * @return true, wenn irgendwo eine Veraenderung vorgenommen wurde, sonst false
     */
	public boolean removeAtomicNegations() {
		if(isNegation()) {
			if(child1.isTrueLeaf()) {
				variable = "false";
				child1 = null;
				return true;
			}
			if(child1.isFalseLeaf()) {
				variable = "true";
				child1 = null;
				return true;
			}
			if(!child1.isLeaf()) {
				return child1.removeAtomicNegations();
			}
		}
		if(isConjunction()) {
			return child1.removeAtomicNegations() | child2.removeAtomicNegations();
		}
		return false;
	}
	
	/**
     * Ersetzt alle Vorkommen von Idempotenzen x∧x durch x aus dem gesamten Baum.
     * @return true, wenn irgendwo eine Veraenderung vorgenommen wurde, sonst false
     */
	public boolean removeIdempotency() {
		if(isConjunction()) {
			if(child1.isLeaf() && child2.isLeaf() && child1.variable.equals(child2.variable)) {
				variable = child1.variable;
				child1 = null;
				child2 = null;
				return true;
			}else {
				return child1.removeIdempotency() | child2.removeIdempotency();
			}
		}
		if(isNegation()) {
			return child1.removeIdempotency();
		}
		return false;
	}
	
	/**
     * Findet und ersetzt alle Vorkommen von Teilformen x∧¬x bzw. ¬x∧x durch false aus dem gesamten Baum.
     * @return true, wenn irgendwo eine Veraenderung vorgenommen wurde, sonst false
     */
	public boolean findBasicContradictions() {
		if(isConjunction()) {
			if(child1.isNegation() && child1.child1.isLeaf() && child2.isLeaf() && child1.child1.variable.equals(child2.variable)) {
				variable = "false";
				child1 = null;
				child2 = null;
				return true;
			}
			if(child2.isNegation() && child2.child1.isLeaf() && child1.isLeaf() && child2.child1.variable.equals(child1.variable)) {
				variable = "false";
				child1 = null;
				child2 = null;
				return true;
			}
			return child1.findBasicContradictions() | child2.findBasicContradictions();
		}
		if(isNegation()) {
			return child1.findBasicContradictions();
		}
		return false;
	}
	
	/**
     * Ersetzt alle Vorkommen von φ∧true bzw. true∧φ durch φ aus dem gesamten Baum.
     * @return true, wenn irgendwo eine Veraenderung vorgenommen wurde, sonst false
     */
	public boolean removeTrueConjuncts() {
		if(isConjunction()) {
			if(child1.isTrueLeaf()) {
				variable = child2.variable;
				child1 = child2.child1;
				child2 = child2.child2;
				return true;
			}
			if(child2.isTrueLeaf()) {
				variable = child1.variable;
				child2 = child1.child2;
				child1 = child1.child1;
				return true;
			}
			return child1.removeTrueConjuncts() | child2.removeTrueConjuncts();
		}
		if(isNegation()) {
			return child1.removeTrueConjuncts();
		}
		return false;
	}
	
	/**
     * Findet und ersetzt alle Vorkommen von φ∧false bzw. false∧φ durch false aus dem gesamten Baum.
     * @return true, wenn irgendwo eine Veraenderung vorgenommen wurde, sonst false
     */
	public boolean findFalseConjuncts() {
		if(isConjunction()) {
			if(child1.isFalseLeaf()) {
				child1 = null;
				child2 = null;
				variable = "false";
				return true;
			}
			if(child2.isFalseLeaf()) {
				child1 = null;
				child2 = null;
				variable = "false";
				return true;
			}
			return child1.findFalseConjuncts() | child2.findFalseConjuncts();
		}
		if(isNegation()) {
			return child1.findFalseConjuncts();
		}
		return false;
	}
	
	/**
	 * Reduziert den Baum so viel wie möglich
	 */
	public void reduce() {
		boolean reducing = true;
		while(reducing) {
			reducing = false;
			if(removeDoubleNegations()) reducing = true;
			if(removeAtomicNegations()) reducing = true;
			if(removeIdempotency()) reducing = true;
			if(findBasicContradictions()) reducing = true;
			if(removeTrueConjuncts()) reducing = true;
			if(findFalseConjuncts()) reducing = true;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * Gibt eine String-Repraesentation des boolschen Binaerbaums zurücl
	 * @return String-Repraesentation des boolschen Binaerbaums
	 */
	 @Override
	 public String toString() {
		if(isLeaf()) {
			return variable;
		}
		if(isNegation()) {
			return "not(" + child1.toString() + ")";
		}
		if(isConjunction()) {
			return "(" + child1.toString() + " and " + child2.toString() + ")";
		}
		return "";
	 }
}
