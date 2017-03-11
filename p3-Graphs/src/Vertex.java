/**
 * Representation of a graph vertex
 */
public class Vertex {
	// label attached to this vertex
	private String label;
	private Integer cost; //Cost for Dijkstra
    private boolean known; //Known flag for Dijkstra
    private Vertex previousVertex; //Previous connected vertex


	/**
	 * Construct a new vertex
     * Sets cost to infinity and known to false
	 * 
	 * @param label
	 *            the label attached to this vertex
	 */
	public Vertex(String label) {
		if (label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
		cost = Integer.MAX_VALUE;
		known = false;
		previousVertex = null;
	}

    /**
     * Set the cost of the vertex
     * @param newCost New cost of the vertex
     */
	public void setCost(Integer newCost) {
	    cost = newCost;
    }

    /**
     * Set the known flag
     * @param b New flag value
     */
    public void setKnown(Boolean b) {
	    known = b;
    }

    /**
     * @return The cost
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * @return Known value
     */
    public boolean isKnown() {
        return known;
    }

    /**
     * Set the previous vertex
     * @param v Previous vertex
     */
    public void setPreviousVertex(Vertex v) {
        previousVertex = v;
    }

    /**
     * @return Previous vertex
     */
    public Vertex getPreviousVertex() {
        return previousVertex;
    }

	/**
	 * Get a vertex label
	 * 
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * A string representation of this object
	 * 
	 * @return the label attached to this vertex
	 */
	public String toString() {
		return label;
	}

	// auto-generated: hashes on label
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	// auto-generated: compares labels
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
			return other.label == null;
		} else {
			return label.equals(other.label);
		}
	}

}
