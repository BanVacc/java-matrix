# Заголовок 1

## Тесты

### Cross product (Векторное произведение)

```math
\begin{pmatrix}
    3 & -3 & 1
\end{pmatrix}
    \times
\begin{pmatrix}
    4 & 9 & 2
\end{pmatrix}
= 
    \begin{pmatrix}
        (-3 \cdot 2 - 1 \cdot 9) &
        (3 \cdot 2 - 1 \cdot 4) &
        (3 \cdot 9 + 3 \cdot 4)
    \end{pmatrix}
=
    \begin{pmatrix}
        -15 & -2 & 39
    \end{pmatrix}
```

### Dot product (Скалярное произведение)

```math
\begin{pmatrix}
    1 & 3 & -5
\end{pmatrix}
    \cdot
\begin{pmatrix}
    4 & -2 & 1
\end{pmatrix}
=
(1 \cdot 4) + (3 \cdot -2) + (-5 \cdot -1)
= 3
```

### Matrix multiplication (Умножение матриц)

```math
\begin{pmatrix}
    1 & 2 & 3 \\
    4 & 5 & 6
\end{pmatrix}
    \cdot
\begin{pmatrix}
    10 & 11 \\
    20 & 21 \\
    30 & 31 \\
\end{pmatrix}
= 
\begin{pmatrix}
    1 \cdot 10 + 2 \cdot 20 + 3 \cdot 30 & 
    1 \cdot 11 + 2 \cdot 21 + 3 \cdot 31 //
    4 \cdot 10 + 5 \cdot 20 + 6 \cdot 30 &
    4 \cdot 11 + 5 \cdot 21 + 6 \cdot 31 //
\end{pmatrix}
=
\begin{pmatrix}
    140 & 146 //
    320 & 335 //
\end{pmatrix}
```