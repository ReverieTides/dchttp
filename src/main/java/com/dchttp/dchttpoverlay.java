//package com.dchttp;
//
//import com.google.common.collect.Iterables;
//import com.google.inject.Inject;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics2D;
//import java.awt.Shape;
//import java.util.Set;
//import net.runelite.api.TileObject;
//import net.runelite.api.coords.WorldPoint;
//import net.runelite.client.ui.overlay.Overlay;
//import net.runelite.client.ui.overlay.OverlayLayer;
//import net.runelite.client.ui.overlay.OverlayPosition;
//import net.runelite.client.ui.overlay.OverlayUtil;
//import net.runelite.client.util.ColorUtil;
//import net.runelite.api.Client;
//
//class HerbiboarOverlay extends Overlay
//{
//    private final dchttpPlugin plugin;
//    private final dchttpConfig config;
//
//    public Client client;
//
//    @Inject
//    public HerbiboarOverlay(dchttpPlugin plugin, dchttpConfig config)
//    {
//        setPosition(OverlayPosition.DYNAMIC);
//        setLayer(OverlayLayer.ABOVE_SCENE);
//        this.plugin = plugin;
//        this.config = config;
//    }
//
//    @Override
//    public Dimension render(Graphics2D graphics)
//    {
//        if (!plugin.isInHerbiboarArea())
//        {
//            return null;
//        }
//
//        HerbiboarSearchSpot.Group currentGroup = plugin.getCurrentGroup();
//        TrailToSpot nextTrail = plugin.getNextTrail();
//        int finishId = plugin.getFinishId();
//
//        // Draw start objects
//        if (config.isStartShown() && (currentGroup == null && finishId == 0))
//        {
//            plugin.getStarts().values().forEach((obj) -> OverlayUtil.renderTileOverlay(graphics, obj, "", config.getStartColor()));
//        }
//
//        // Draw trails
//        if (config.isTrailShown())
//        {
//            Set<Integer> shownTrailIds = plugin.getShownTrails();
//            plugin.getTrails().values().forEach((x) ->
//            {
//                int id = x.getId();
//                if (shownTrailIds.contains(id) && (finishId > 0 || nextTrail != null && !nextTrail.getFootprintIds().contains(id)))
//                {
//                    OverlayUtil.renderTileOverlay(graphics, x, "", config.getTrailColor());
//                }
//            });
//        }
//
//        // Draw trail objects (mushrooms, mud, etc)
//        if (config.isObjectShown() && !(finishId > 0 || currentGroup == null))
//        {
//            WorldPoint correct = Iterables.getLast(plugin.getCurrentPath()).getLocation();
//            TileObject object = plugin.getTrailObjects().get(correct);
//            drawObjectLocation(graphics, object, config.getObjectColor());
//        }
//
//        // Draw finish tunnels
//        if (config.isTunnelShown() && finishId > 0)
//        {
//            WorldPoint finishLoc = plugin.getEndLocations().get(finishId - 1);
//            TileObject object = plugin.getTunnels().get(finishLoc);
//            drawObjectLocation(graphics, object, config.getTunnelColor());
//        }
//
//        return null;
//    }
//
//    private void drawObjectLocation(Graphics2D graphics, TileObject object, Color color)
//    {
//        if (object == null)
//        {
//            return;
//        }
//
//        if (config.showClickBoxes())
//        {
//            Shape clickbox = object.getClickbox();
//            if (clickbox != null)
//            {
//                Color clickBoxColor = ColorUtil.colorWithAlpha(color, color.getAlpha() / 12);
//
//                graphics.setColor(color);
//                graphics.draw(clickbox);
//                graphics.setColor(clickBoxColor);
//                graphics.fill(clickbox);
//            }
//        }
//        else
//        {
//            OverlayUtil.renderTileOverlay(graphics, object, "", color);
//        }
//    }
//}