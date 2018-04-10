public class PathList <E extends Object> {
    private Element<E> first,last;
    private int size;

    private static class Element<E> {
            E item;
            Element<E> next;
            Element<E> previous;

            Element(E element, Element<E> previous, Element<E> next) {
                this.item = element;
                this.next = next;
                this.previous = previous;
            }
        }
        
        public PathList() {  

            this.size = 0;
            this.first = this.last = null;  
        }  
          
        public void add(E item) { 
            if(item == null){
                System.out.println("End of list, failed to complete.");
                return;
            }
            Element<E> oldLast = last;
            Element<E> newNode = new Element<E>(item,last, null);
            last = newNode;
            //if oldLast is empty, it must be first time add element into list.
            if (oldLast == null){
                first = newNode;
            }else{
                oldLast.next = newNode;
            }
            size++;
        }
        public E get(int index) {
            if(isOutOfrange(index)){
                System.err.println("<Error operation>(PathList): The index is out of current capacity.");
                return null;
            }
            int curIndex = 0;
            Element<E> current = this.first;
            while(current!=null){
                if(index == curIndex){
                    return current.item;
                }
                current = current.next;
                curIndex++;
            }
            return null;
        }
        
        public int indexOf(Object o) {
            int index = 0;
            if (o != null) {
                Element<E> element = this.first;
                if(element != null){
                    while(element!=null){
                        if(o.equals(element.item)){
                            return index;
                        }
                        element = element.next;
                        index++;
                    }
                }
            }
            return -1;
        }
        
        public boolean remove(Object o) {
            if (o != null) {
                Element<E> element = this.first;
                while(element != null){
                    if(o.equals(element.item)){
                        final Element<E> next = element.next;
                        final Element<E> previous = element.previous;
                        //if previous is null, the current must be the first element
                        if (previous == null) {
                            first = next;
                        } else {
                            previous.next = next;
                            element.previous = null;
                        }
                        //if next is null the current must be the last element
                        if (next == null) {
                            last = previous;
                        } else {
                            next.previous = previous;
                            element.next = null;
                        }
                        size--;
                        return true;
                    }
                    element = element.next;
                }
            }
            return false;
        }

        public E getFirst() {

            if (first == null)
                return null;
            else
                return first.item;
        }

        public boolean contains(Object o) {
            return indexOf(o) != -1;
        }

        public int size() {
            return size;
        }

        private boolean isOutOfrange(int index) {  
            return (index > size || index < 0);  
                 
        } 

    }
