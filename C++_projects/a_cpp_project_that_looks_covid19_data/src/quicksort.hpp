#ifndef QUICKSORT_HPP
#define QUICKSORT_HPP

#include <cstdlib>
#include <vector>
#include <algorithm>

template <typename T, typename Cmp>
void quicsort_generic(std::vector<T> &v, const Cmp &cmp);

// -------------------------- Template Implementations ----------------------------
template <typename T, typename Cmp>
int partition(std::vector<T> &v, int lo, int hi, const Cmp &cmp)
{
        int i = lo;
        int j = hi+1;

        while (true) {
                while (cmp(v[++i], v[lo]))
                        if (i == hi) break;

                while (cmp(v[lo], v[--j]))
                        if (j == lo) break;

                if (i >= j) break;
                std::swap(v[i], v[j]);
        }

        std::swap(v[lo], v[j]);
        return j;
}

template <typename T, typename Cmp>
void quicksort_r(std::vector<T> &v, int lo, int hi, const Cmp &cmp)
{
        if (hi <= lo)
                return;

        int j = partition(v, lo, hi, cmp);
        quicksort_r(v, lo, j - 1, cmp);
        quicksort_r(v, j + 1, hi, cmp);
}


template <typename T, typename Cmp>
void quicksort_generic(std::vector<T> &v, const Cmp &cmp)
{
        std::random_shuffle(v.begin(), v.end());
        quicksort_r(v, 0, v.size() - 1, cmp);
}

#endif
