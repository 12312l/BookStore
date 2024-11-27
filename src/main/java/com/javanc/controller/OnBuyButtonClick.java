package com.javanc.controller;

import com.javanc.DTO.ProductDTO;

@FunctionalInterface
public interface OnBuyButtonClick
{
    void onBuy(ProductDTO product, int quantity);
}
