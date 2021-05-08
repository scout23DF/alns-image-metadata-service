package de.com.up42.codingchallenge.imagemetadata.repositories.impl;

import de.com.up42.codingchallenge.imagemetadata.repositories.ImageMetadataRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ImageMetadataRepositoryImpl implements ImageMetadataRepository {

    // private final DatabaseClient databaseClient;

    private static final String SQL_ROYALTIES_ASSETS_BY_AUTHOR =
         "select" +
           " year(article.DT_PUBLISHING)                 as referenceYear, " +
           " monthname(article.DT_PUBLISHING)            as referenceMonth, " +
         //  " year(refasset.DT_REFERENCE_START)           as referenceYear, " +
         //  " monthname(refasset.DT_REFERENCE_START)      as referenceMonth, " +
           " author.ID                                   as authorId, " +
           " author.DS_NAME                              as authorName, " +
           " count(asset.DS_URL)                         as amountOfAssetsReferencedInPublishedArticles, " +
           " sum(asset.VL_PUBLISHING_PRICE)              as totalRoyaltiesValue " +
          "from " +
           "   TB_AUTHOR author " +
           "       inner join TB_ASSET asset on asset.AUTHOR_ID = author.ID " +
           "       inner join TB_REFERENCED_ASSET refasset on refasset.ASSET_URL = asset.DS_URL " +
           "       inner join TB_NEWS_ARTICLE article on article.ID = refasset.ARTICLE_ID " +
          "where 1 = 1 " +
          "  and article.IS_PUBLISHED = TRUE " +
          "  and article.DT_PUBLISHING <= current_timestamp() " +
         // "   and refasset.DT_REFERENCE_START <= current_timestamp() " +
         "group by " +
         "  referenceYear, " +
         "  referenceMonth, " +
         "  authorId, " +
         "  authorName " +
         "order by " +
         "  referenceYear, " +
         "  referenceMonth, " +
         "  authorName ";

    public ImageMetadataRepositoryImpl() {


        }


}
