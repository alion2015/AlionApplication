package com.alion.blockcanary.internal;

import java.util.LinkedList;

/**
 * Created by alion on 2018/11/28.
 */

public class LruLinkedList<E> extends LinkedList<E> {

    int maxCount;
    int position = 0;

    public LruLinkedList(int maxCount) {
        this.maxCount = maxCount;
    }
    public int getMaxCount() {
        return maxCount;
    }

    @Override
    public E get(int index) {
        if(index>-1) {
            return super.get(index % Math.min(maxCount,size()));
        }else{
            return null;
        }
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public E set(int index, E element) {
        return super.set(index% maxCount, element);
    }

    @Override
    public boolean add(E e) {
        if(size()<maxCount){
            return super.add(e);
        }else{
            return set(position++% maxCount,e)!=null;
        }
    }
    public LinkedList<E> getAll(){
        LinkedList<E> first = new LinkedList<>();
        for(int i=position;i<position+size();i++){
            first.add(get(i));
        }
        return  first;
    }
}
