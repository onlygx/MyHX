package com.example.viewpagerdemo.ui.jlfragmenwork.city;

import com.example.viewpagerdemo.ui.jlfragmenwork.city.ContactItemInterface;

import java.util.Comparator;

public class ContactItemComparator implements Comparator<ContactItemInterface>
{

	@Override
	public int compare(ContactItemInterface lhs, ContactItemInterface rhs)
	{
		if (lhs.getItemForIndex() == null || rhs.getItemForIndex() == null)
			return -1;

		return (lhs.getItemForIndex().compareTo(rhs.getItemForIndex()));

	}

}
