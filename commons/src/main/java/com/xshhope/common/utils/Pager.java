package com.xshhope.common.utils;

import java.util.List;

/**
 * @author xshhope
 */
public class Pager<T> {
    private int currentPageNumber;
    private int perPageSize;
    private long totalElements;
    private List<T> data;

    public Pager() {
        this.currentPageNumber = 0;
        this.perPageSize = 10;
    }

    public Pager(int currentPageNumber, int perPageSize) {
        this.currentPageNumber = currentPageNumber;
        this.perPageSize = perPageSize;
    }

    public List<T> getData() {
        return this.data;
    }

    public Pager<T> setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public Pager<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public int getCurrentPageNumber() {
        return this.currentPageNumber;
    }

    public int getPerPageSize() {
        return this.perPageSize;
    }

    public long getTotalPages() {
        return this.perPageSize == 0 ? 0L : this.totalElements / (long) this.perPageSize + (long) (this.totalElements % (long) this.perPageSize == 0L ? 0 : 1);
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public boolean getHasPrev() {
        return this.getCurrentPageNumber() > 0;
    }

    public boolean getHasNext() {
        return (long) (this.getCurrentPageNumber() + 1) < this.getTotalPages();
    }

    public SimplePager<T> toSimplePager() {
        SimplePager<T> p = new SimplePager();
        p.setCurrentPageNumber(this.getCurrentPageNumber());
        p.setPerPageSize(this.getPerPageSize());
        p.setTotalElements(this.getTotalElements());
        p.setData(this.getData());
        return p;
    }

    public class SimplePager<T> {
        private int currentPageNumber;
        private int perPageSize;
        private long totalElements;
        private List<T> data;

        public SimplePager() {
        }

        public int getCurrentPageNumber() {
            return this.currentPageNumber;
        }

        public void setCurrentPageNumber(int currentPageNumber) {
            this.currentPageNumber = currentPageNumber;
        }

        public int getPerPageSize() {
            return this.perPageSize;
        }

        public void setPerPageSize(int perPageSize) {
            this.perPageSize = perPageSize;
        }

        public long getTotalElements() {
            return this.totalElements;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public List<T> getData() {
            return this.data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }
    }
}
