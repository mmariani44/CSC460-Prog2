class Bucket {
	public DirectoryNode node;
	public int count;
	
	public Bucket() {
		this.node = null;
		this.count = 0;
	}
}

public class DirectoryNode {
	Bucket[] buckets;
	
	public DirectoryNode() {
		buckets[10] = null;
		for (int i = 0; i < 10; i++) {
			buckets[i] = new Bucket();
		}
	}
	
	public void AddItem(String item, DirectoryNode node, int trailing_index, int offset) {
		//you need to add the actual item into the tree somewhere, idiot, how else will
		//you know what to do with an id that only goes in a bucket where it doesnt traverse the whole length of the number
		
		
		//find correct bucket
		//if bucket is full, make new bucket and add it there (repeating if necessary)
		int trailing_digit = Integer.valueOf(item.charAt(trailing_index));
		if (buckets[trailing_digit].count >= 50) {
			if (buckets[trailing_digit].node == null) {
				buckets[trailing_digit].node = new DirectoryNode();
			}
			AddItem(item, buckets[trailing_digit].node, trailing_index--, offset);
		}
		else {
			buckets[trailing_digit].count++;
		}
	}
}
