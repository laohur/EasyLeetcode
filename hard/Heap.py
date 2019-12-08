
class MaxHeap():
    def __init__(self):
        self.data=[]
        self.count=len(self.data)
    def size(self):
        return self.count
    def peak(self):
        return self.data[0]
    def push(self,e):
        if len(self.data)==self.count:
            self.data.append(e)
        else:
            self.data[self.count]=e
        self.count+=1
        self.shiftup(self.count-1)
    def pop(self):
        if self.size()<=0:
            return None
        e=self.data[0]
        self.data[0]=self.data[-1]
        self.data[-1]=-1
        self.count-=1
        self.shiftdown(0)
        return e

    def shiftup(self,i): # sure data[p]>i
        p=(i-1)>>1
        while i>0 and self.data[p]<self.data[i]:
            self.data[p],self.data[i]=self.data[i],self.data[p]
            i=p
            p=(i-1)>>1

    def shiftdown(self,i):
        j=(i<<1)+1
        while j<self.count:
            if j+1<self.count and self.data[j+1]>self.data[j]:
                j+=1
            if self.data[i]>=self.data[j]:
                break
            self.data[i],self.data[j]=self.data[j],self.data[i]
            i=j
            j=(i<<1)+1
