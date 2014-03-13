/*******************************************************************************
 * Software Name : RCS IMS Stack
 *
 * Copyright (C) 2010 France Telecom S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.orangelabs.rcs.provider.fthttp;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author YPLO6403
 * 
 *         FtHttpResume is the abstract base class for all FT HTTP resume classes
 */
public abstract class FtHttpResume {

	/**
	 * The date of creation
	 */
	final protected Date date;
	/**
	 * The direction
	 */
	final protected Direction direction;
	/**
	 * The filename
	 */
	final protected String filename;

	/**
	 * The thumbnail
	 */
	final protected byte[] thumbnail;

	/**
	 * The remote contact number
	 */
	final private String contact;
	/**
	 * the display name
	 */
	final private String displayName;

	/**
	 * the list of participants separated by a ';' or null
	 */
	final private String participants;

	/**
	 * the Chat Id
	 */
	final private String chatId;

	/**
	 * the session Id
	 */
	final private String sessionId;

	/**
	 * Works just like FtHttpResume(Direction,String,byte[],String,String,String,String,List,Date) except the date is always null
	 * 
	 * @see #FtHttpResume(Direction,String,byte[],String,String,String,String,List,Date)
	 */
	public FtHttpResume(Direction direction, String filename, byte[] thumbnail, String contact, String displayName, String chatId,
			String sessionId, String participants) {
		this(direction, filename, thumbnail, contact, displayName, chatId, sessionId, participants, null);
	}

	/**
	 * Creates an instance of FtHttpResume Data Object
	 * 
	 * @param direction
	 *            the {@code direction} value.
	 * @param file
	 *            the {@code file} value.
	 * @param thumbnail
	 *            the {@code thumbnail} byte array.
	 * @param contact
	 *            the {@code contact} value.
	 * @param displayName
	 *            the {@code displayName} value.
	 * @param chatId
	 *            the {@code chatId} value.
	 * @param sessionId
	 *            the {@code sessionId} value.
	 * @param participants
	 *            the list of {@code participants}.
	 * @param date
	 *            the {@code date} value.
	 */
	public FtHttpResume(Direction direction, String filename, byte[] thumbnail, String contact, String displayName, String chatId,
			String sessionId, String participants, Date date) {
		if (direction == null || filename == null)
			throw new IllegalArgumentException("Null argument");
		this.date = date;
		this.direction = direction;
		this.filename = filename;
		this.thumbnail = thumbnail;
		this.contact = contact;
		this.displayName = displayName;
		this.chatId = chatId;
		this.sessionId = sessionId;
		this.participants = participants;
	}

	/**
	 * Creates a FtHttpResumeUploadGc data object
	 * 
	 * @param cursor
	 *            the {@code cursor} value.
	 */
	public FtHttpResume(FthttpCursor cursor) {
		if (cursor.getDirection() == null || cursor.getFilename() == null)
			throw new IllegalArgumentException("Null argument");
		this.date = cursor.getDate();
		this.direction = cursor.getDirection();
		this.filename = cursor.getFilename();
		this.thumbnail = cursor.getThumbnail();
		this.contact = cursor.getContact();
		this.displayName = cursor.getDisplayName();
		this.chatId = cursor.getChatid();
		this.sessionId = cursor.getSessionId();
		this.participants = cursor.getParticipants();
	}

	public Date getDate() {
		return date;
	}

	public Direction getDirection() {
		return direction;
	}

	public String getFilename() {
		return filename;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public String getContact() {
		return contact;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getChatId() {
		return chatId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public List<String> getParticipants() {
		return getParticipants(participants);
	}

	/**
	 * Convert a string with items separated by ";" into a list
	 * 
	 * @param participants
	 *            the {@code participants} value.
	 * @return the list of participants
	 */
	private static List<String> getParticipants(final String participants) {
		if (participants != null && participants.trim().length() > 0) {
			String[] array = participants.split(";", 0);
			if (array != null && array.length > 0) {
				return Arrays.asList(array);
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "FtHttpResume [date=" + date + ", dir=" + direction + ", file=" + filename + "]";
	}

}
