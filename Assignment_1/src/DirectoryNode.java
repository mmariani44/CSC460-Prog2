class Bucket {
	public DirectoryNode node;
	public int count;
	
	public Bucket() {
		this.node = null;
		this.count = 0;
	}
}

public class DirectoryNode {
	Bucket bucket_0;
	Bucket bucket_1;
	Bucket bucket_2;
	Bucket bucket_3;
	Bucket bucket_4;
	Bucket bucket_5;
	Bucket bucket_6;
	Bucket bucket_7;
	Bucket bucket_8;
	Bucket bucket_9;
	
	public DirectoryNode() {
		bucket_0 = new Bucket();
		bucket_1 = new Bucket();
		bucket_2 = new Bucket();
		bucket_3 = new Bucket();
		bucket_4 = new Bucket();
		bucket_5 = new Bucket();
		bucket_6 = new Bucket();
		bucket_7 = new Bucket();
		bucket_8 = new Bucket();
		bucket_9 = new Bucket();
	}
	
	public void AddItem(String item, DirectoryNode node, int leading_index) {
		//you need to add the actual item into the tree somewhere, idiot, how else will
		//you know what to do with an id that only goes in a bucket where it doesnt traverse the whole length of the number
		//also, make sure when you do add the number, that you reverse its order again. (ALL OF THIS IS ASSUMING THE NUMBER HAS BEEN PRE-REVERSED)
		//(I MEAN i guess i could just do this "leading index" thing as a "trailing index" thing instead, that way i dont go crazy with the flipping...
		//also that seems better for modularity
		//ok ill do that TODO
		
		//THIS WHOLE "BUCKETS 0-9" THING SUCKS BIG TIME why would i not just make an array of bucket objects????? TODO
		
		//TODO all todos are tomorrow me's problems because it is too late for today me
		
		//find correct bucket
		//if bucket is full, make new bucket and add it there (repeating if necessary)
		
		if (item.charAt(leading_index) == '0') {
			if (bucket_0.count >= 50) {
				if (bucket_0.node == null) {
					bucket_0.node = new DirectoryNode();
				}
				AddItem(item, bucket_0.node, leading_index++);
			}
			else {
				bucket_0.count++;
			}
		}
		else if (item.charAt(leading_index) == '1') {
			if (bucket_1.count >= 50) {
				if (bucket_1.node == null) {
					bucket_1.node = new DirectoryNode();
				}
				AddItem(item, bucket_1.node, leading_index++);
			}
			else {
				bucket_1.count++;
			}
		}
		else if (item.charAt(leading_index) == '2') {
			if (bucket_2.count >= 50) {
				if (bucket_2.node == null) {
					bucket_2.node = new DirectoryNode();
				}
				AddItem(item, bucket_2.node, leading_index++);
			}
			else {
				bucket_2.count++;
			}
		}
		else if (item.charAt(leading_index) == '3') {
			if (bucket_3.count >= 50) {
				if (bucket_3.node == null) {
					bucket_3.node = new DirectoryNode();
				}
				AddItem(item, bucket_3.node, leading_index++);
			}
			else {
				bucket_3.count++;
			}
		}
		else if (item.charAt(leading_index) == '4') {
			if (bucket_4.count >= 50) {
				if (bucket_4.node == null) {
					bucket_4.node = new DirectoryNode();
				}
				AddItem(item, bucket_4.node, leading_index++);
			}
			else {
				bucket_4.count++;
			}
		}
		else if (item.charAt(leading_index) == '5') {
			if (bucket_5.count >= 50) {
				if (bucket_5.node == null) {
					bucket_5.node = new DirectoryNode();
				}
				AddItem(item, bucket_5.node, leading_index++);
			}
			else {
				bucket_5.count++;
			}
		}
		else if (item.charAt(leading_index) == '6') {
			if (bucket_6.count >= 50) {
				if (bucket_6.node == null) {
					bucket_6.node = new DirectoryNode();
				}
				AddItem(item, bucket_6.node, leading_index++);
			}
			else {
				bucket_6.count++;
			}
		}
		else if (item.charAt(leading_index) == '7') {
			if (bucket_7.count >= 50) {
				if (bucket_7.node == null) {
					bucket_7.node = new DirectoryNode();
				}
				AddItem(item, bucket_7.node, leading_index++);
			}
			else {
				bucket_7.count++;
			}
		}
		else if (item.charAt(leading_index) == '8') {
			if (bucket_8.count >= 50) {
				if (bucket_8.node == null) {
					bucket_8.node = new DirectoryNode();
				}
				AddItem(item, bucket_8.node, leading_index++);
			}
			else {
				bucket_8.count++;
			}
		}
		else if (item.charAt(leading_index) == '9') {
			if (bucket_9.count >= 50) {
				if (bucket_9.node == null) {
					bucket_9.node = new DirectoryNode();
				}
				AddItem(item, bucket_9.node, leading_index++);
			}
			else {
				bucket_9.count++;
			}
		}
	}
}
