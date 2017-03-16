// https://leetcode.com/problems/palindrome-linked-list/

package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class No234_PalindromeLinkedList {

    @Test
    public void test() throws Exception {
        No234_PalindromeLinkedList instance = new No234_PalindromeLinkedList();
        ListNode head = null;
        assertTrue(instance.isPalindrome(head));
        head = new ListNode(1);
        assertTrue(instance.isPalindrome(head));
        head.next = new ListNode(2);
        assertFalse(instance.isPalindrome(head));
        head.next.next = new ListNode(1);
        assertTrue(instance.isPalindrome(head));
        head.next.next.next = new ListNode(3);
        assertFalse(instance.isPalindrome(head));
        head.next.next.next.next = new ListNode(1);
        assertFalse(instance.isPalindrome(head));
        head.next.next.next.next.next = new ListNode(2);
        assertFalse(instance.isPalindrome(head));
        head.next.next.next.next.next.next = new ListNode(1);
        assertTrue(instance.isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;

        ListNode node = head;
        ListNode newHead = new ListNode(head.val);
        while(node.next != null) {
            ListNode temp = new ListNode(node.next.val);
            temp.next = newHead;
            newHead = temp;
            node = node.next;
        }

        while(head != null) {
            if (head.val != newHead.val) return false;
            head = head.next;
            newHead = newHead.next;
        }
        return true;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}