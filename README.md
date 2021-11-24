# Linear_Computing_dual_form
This algorithm was created to convert a linear program of this form:

min(max) c^Τx

s.t. Ax ⊕ b

x≥0

to its dual form:

max(min) newC^Τ y

s.t. A^T y ⊕ newB   

y<=0 or y>=0 or free variable

Important note #1:
- The input file should be the output file of the LinearComputing_matrix_form algorithm.
- In the first line of the output from the LinearComputing_matrix_form, a space should be added between "(" and the first element.

So instead of: max [(-1 0 -200 75 0 0 )]^T, there should be something like this: max [( -1 0 -200 75 0 0 )]^T 
