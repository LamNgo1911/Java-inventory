package integrify.inventory.application.shared;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class OffsetPage implements Pageable, Serializable {

    private final long limit;
    private final long offset;
    private final Sort sort;

    public OffsetPage(long limit, int offset, Sort sort) {
        if(offset < 0){
            throw new IllegalArgumentException("Offset index must not be less than zero");
        }

        if(limit < 1){
            throw new IllegalArgumentException("Limit must not be less than one");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }
    public OffsetPage(long limit, int offset) {
        if(offset < 0){
            throw new IllegalArgumentException("Offset index must not be less than zero");
        }

        if(limit < 1){
            throw new IllegalArgumentException("Limit must not be less than one");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = Sort.unsorted();
    }

    @Override
    public int getPageNumber() {
        return (int)(offset/limit);
    }

    @Override
    public int getPageSize() {
        return (int)limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetPage(getOffset() + getPageSize(), getPageSize(), getSort());
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? new OffsetPage(getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }

    @Override
    public Pageable first() {
        return new OffsetPage(0, getPageSize(), getSort());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new OffsetPage(pageNumber, this.getPageSize(), this.getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}

