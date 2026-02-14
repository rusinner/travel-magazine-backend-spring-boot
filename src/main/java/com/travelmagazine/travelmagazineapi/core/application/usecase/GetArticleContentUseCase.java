package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.model.enums.SubscriptionTier;
import com.travelmagazine.travelmagazineapi.core.domain.model.enums.UserRole;
import org.springframework.stereotype.Service;

@Service
public class GetArticleContentUseCase {

    public String execute(Article article, User currentUser) {
        // Η ΛΟΓΙΚΗ ΓΙΑ ΤΟ ΜΕΛΛΟΝ:
        // Αν το άρθρο είναι Premium ΚΑΙ ο χρήστης ΔΕΝ είναι PRO ή ADMIN
        if (article.isPremium() && !isAuthorized(currentUser)) {
            // Προς το παρόν επιστρέφουμε όλο το κείμενο (όπως ζήτησες)
            // Αργότερα εδώ θα επιστρέφουμε μόνο ένα "Preview"
            return article.getContent();
        }

        return article.getContent();
    }

    private boolean isAuthorized(User user) {
        if (user == null) return false;
        return user.getRole() == UserRole.ADMIN ||
                user.getSubscriptionTier() == SubscriptionTier.PRO;
    }
}