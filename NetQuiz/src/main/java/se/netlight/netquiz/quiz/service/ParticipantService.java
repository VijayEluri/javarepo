/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.quiz.service;

import se.netlight.netquiz.quiz.model.Participant;

/**
 *
 * @author mire
 */
public interface ParticipantService {

    Participant findByEmailAndUniqueKey(String email, String uniqueKey);
    
    void saveOrUpdate(Participant p);
}
